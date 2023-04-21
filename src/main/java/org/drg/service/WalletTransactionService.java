package org.drg.service;

import org.apache.commons.lang3.StringUtils;
import org.drg.dao.TransactionDao;
import org.drg.dao.WalletDao;
import org.drg.dao.WalletEventDao;
import org.drg.entity.Transaction;
import org.drg.entity.Wallet;
import org.drg.entity.WalletEvent;
import org.drg.enums.TransactionFlag;
import org.drg.enums.TransactionType;
import org.drg.enums.WalletEventType;
import org.drg.enums.WalletFlag;
import org.drg.exceptions.ValidationException;
import org.drg.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Service
public class WalletTransactionService {
	private final WalletDao walletDao;
	private final TransactionDao transactionDao;
	private final WalletEventDao walletEventDao;
	@Value("${single_transaction_limit}")
	private BigInteger singleTransactionLimit;
	@Value("${suspicious_transactions}")
	private BigInteger suspiciousTransaction;
	@Value("${daily_withdrawal_limit}")
	private BigInteger dailyWithdrawalLimit;
	@Value("${number_of_transactions_suspicious}")
	private Integer numberOfTransactionsSuspicious;
	@Value("${number_of_transactions_block}")
	private Integer numberOfTransactionsBlock;
	private final Clock systemUTC = Clock.systemUTC();

	public WalletTransactionService(@Autowired WalletDao walletDao, @Autowired TransactionDao transactionDao, @Autowired WalletEventDao walletEventDao) {
		this.walletDao = walletDao;
		this.transactionDao = transactionDao;
		this.walletEventDao = walletEventDao;
	}

	private void validateId(Integer id) {
		if (Objects.isNull(id) || id < 0) {
			throw new ValidationException("Invalid id.");
		}
	}

	private void validateCreation(Wallet wallet) {
		if (Objects.isNull(wallet)) {
			throw new ValidationException("Object is null.");
		}
		if (StringUtils.isEmpty(wallet.getEmail())) {
			throw new ValidationException("E-mail is empty.");
		}
		if (StringUtils.isEmpty(wallet.getPhone())) {
			throw new ValidationException("Phone is empty.");
		}
		if (StringUtils.isEmpty(wallet.getFirstName())) {
			throw new ValidationException("First name is empty.");
		}
		if (StringUtils.isEmpty(wallet.getLastName())) {
			throw new ValidationException("Last name is empty.");
		}
		if (Objects.isNull(wallet.getCurrency())) {
			throw new ValidationException("Currency is invalid.");
		}
	}

	private void validateTransaction(Transaction transaction) {
		if (Objects.isNull(transaction)) {
			throw new ValidationException("Object is null.");
		}

		validateId(transaction.getWalletId());

		Wallet wallet = readById(transaction.getWalletId());
		if (null == wallet) {
			throw new ValidationException("Wallet doesn't exist.");
		}
		if (Objects.isNull(transaction.getCurrency())) {
			throw new ValidationException("Currency is invalid.");
		}
		if (Objects.isNull(transaction.getTransactionType())) {
			throw new ValidationException("Transaction type is invalid.");
		}
		if (Objects.isNull(transaction.getAmount()) || transaction.getAmount()
				.compareTo(BigInteger.ZERO) < 1) {
			throw new ValidationException("Amount is 0 or less than 0.");
		}

		validateAvailableBalance(transaction);
		validateSingleTransactionLimit(transaction.getAmount());
		validateDailyWithdrawalLimit(transaction);
		flagForSuspiciousTransaction(transaction);
		checkForBlockedWallet(wallet.getId());
	}

	private void checkForBlockedWallet(Integer walletId) {
		WalletEvent walletEvent = getLastEventToday(walletId);
		if (walletEvent != null && WalletEventType.BLOCKED.equals(walletEvent.getWalletEventType())) {
			throw new ValidationException("Wallet is blocked.");
		}

	}

	private void validateDailyWithdrawalLimit(Transaction transaction) {
		if (transaction.getTransactionType()
				.equals(TransactionType.WITHDRAWAL)) {
			LocalDateTime beginningOfDay = LocalDateTime.now(systemUTC)
					.with(LocalTime.MIN);

			BigInteger sumAmountAtDate = transactionDao.sumAmountAtDate(transaction.getWalletId(), ConverterUtil.stringFromLocalDateTime(beginningOfDay));

			if (sumAmountAtDate != null && transaction.getAmount()
					.add(sumAmountAtDate)
					.compareTo(dailyWithdrawalLimit) == 1) {
				throw new ValidationException("Daily withdrawal limit is " + dailyWithdrawalLimit.divide(BigInteger.valueOf(100)));
			}
		}
	}

	private void validateAvailableBalance(Transaction transaction) {
		if (transaction.getTransactionType()
				.equals(TransactionType.WITHDRAWAL)) {
			BigInteger balance = readById(transaction.getWalletId()).getBalance();

			if (transaction.getAmount()
					.compareTo(balance) == 1) {
				throw new ValidationException("Insufficient funds.");
			}
		}
	}

	private void flagForSuspiciousTransaction(Transaction transaction) {
		if (transaction.getAmount()
				.compareTo(suspiciousTransaction) == 1) {
			transaction.setFlag(TransactionFlag.SUSPICIOUS);
		}
	}

	private void validateSingleTransactionLimit(BigInteger amount) {
		if (amount.compareTo(singleTransactionLimit) == 1) {
			throw new ValidationException("Single transaction limit is " + singleTransactionLimit.divide(BigInteger.valueOf(100)));
		}
	}

	public Wallet readById(Integer id) {
		validateId(id);
		return walletDao.readById(id);
	}

	public void createWallet(Wallet wallet) {
		validateCreation(wallet);
		validateUniquePhoneEmail(wallet);
		walletDao.create(wallet);
	}

	private void validateUniquePhoneEmail(Wallet wallet) {
		Wallet walletInDb = walletDao.readByPhoneOrEmail(wallet.getPhone(), wallet.getEmail(), wallet.getCurrency());
		if (!Objects.isNull(walletInDb)) {
			throw new ValidationException("Wallet already exists.");
		}
	}

	public void createTransaction(Transaction transaction) {
		validateTransaction(transaction);
		transactionDao.create(transaction);
		if (!Objects.isNull(transaction.getId())) {
			Wallet wallet = readById(transaction.getWalletId());
			updateWalletBalance(transaction, wallet);
			checkNumberOfTransactionsForSuspicious(wallet);
			checkNumberOfTransactionsForBlock(wallet);
		}
	}

	private void checkNumberOfTransactionsForSuspicious(Wallet wallet) {
		LocalDateTime date = LocalDateTime.now(systemUTC)
				.minusHours(1);

		Integer numberOfTransactions = transactionDao.numberOfTransactions(wallet.getId(), ConverterUtil.stringFromLocalDateTime(date));

		if (!WalletFlag.SUSPICIOUS.equals(wallet.getFlag()) && numberOfTransactions != null && numberOfTransactions > numberOfTransactionsSuspicious) {
			wallet.setFlag(WalletFlag.SUSPICIOUS);
			walletDao.update(wallet);
		}
	}

	private void checkNumberOfTransactionsForBlock(Wallet wallet) {
		LocalDateTime date = getStartDateForBlock(wallet.getId());

		Integer numberOfTransactions = transactionDao.numberOfTransactions(wallet.getId(), ConverterUtil.stringFromLocalDateTime(date));

		if (numberOfTransactions != null && numberOfTransactions > numberOfTransactionsBlock) {
			walletEventDao.create(WalletEvent.builder()
					.walletEventType(WalletEventType.BLOCKED)
					.walletId(wallet.getId())
					.build());
		}
	}

	private LocalDateTime getStartDateForBlock(Integer walletId) {
		WalletEvent walletEvent = getLastEventToday(walletId);

		if (walletEvent != null && WalletEventType.UNBLOCKED.equals(walletEvent.getWalletEventType())) {
			return walletEvent.getDate();
		}

		return LocalDate.now(systemUTC)
				.atStartOfDay();
	}

	private WalletEvent getLastEventToday(Integer walletId) {
		LocalDateTime beginningOfDay = LocalDate.now(systemUTC)
				.atStartOfDay();
		LocalDateTime endOfDay = LocalDate.now(systemUTC)
				.atTime(LocalTime.MAX);
		return walletEventDao.readLastEventByDate(walletId, ConverterUtil.stringFromLocalDateTime(beginningOfDay),
				ConverterUtil.stringFromLocalDateTime(endOfDay));
	}

	private void updateWalletBalance(Transaction transaction, Wallet wallet) {
		BigInteger balance = wallet.getBalance();
		if (transaction.getTransactionType()
				.equals(TransactionType.DEPOSIT)) {
			balance = balance.add(transaction.getAmount());
		} else {
			balance = balance.subtract(transaction.getAmount());
		}
		wallet.setBalance(balance);
		walletDao.update(wallet);
	}

	public Boolean resetWalletBlock(Integer walletId) {
		validateId(walletId);
		WalletEvent walletEvent = getLastEventToday(walletId);

		if (walletEvent != null && WalletEventType.BLOCKED.equals(walletEvent.getWalletEventType())) {
			walletEventDao.create(WalletEvent.builder()
					.walletEventType(WalletEventType.UNBLOCKED)
					.walletId(walletId)
					.build());
			return true;
		}
		return false;
	}

}
