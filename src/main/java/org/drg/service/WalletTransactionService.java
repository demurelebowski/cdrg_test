package org.drg.service;

import org.apache.commons.lang3.StringUtils;
import org.drg.dao.TransactionDao;
import org.drg.dao.WalletDao;
import org.drg.entity.Transaction;
import org.drg.entity.Wallet;
import org.drg.enums.TransactionType;
import org.drg.exceptions.ValidationException;
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
	@Value("${single_transaction_limit}")
	private BigInteger singleTransactionLimit;
	@Value("${suspicious_transactions}")
	private BigInteger suspiciousTransaction;

	public WalletTransactionService(@Autowired WalletDao walletDao, @Autowired TransactionDao transactionDao) {
		this.walletDao = walletDao;
		this.transactionDao = transactionDao;
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

		if (null == readById(transaction.getWalletId())) {
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
		flagForSuspiciousTransaction(transaction);
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
			transaction.setFlag("Suspicious");
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
		checkNumberOfTransactions(transaction.getWalletId());

		/*validateTransaction(transaction);
		transactionDao.create(transaction);
		if (!Objects.isNull(transaction.getId())) {
			updateWalletBalance(transaction);
			checkNumberOfTransactions(transaction.getWalletId());
		}*/
	}

	private void checkNumberOfTransactions(Integer walletId) {
		LocalDateTime now = LocalDateTime.now(Clock.systemUTC());
		LocalDateTime date = now.minusHours(1);
		LocalDateTime beginningOfDay = now.with(LocalTime.MIN);

		Integer numberOfTransactions = transactionDao.numberOfTransactions(walletId, date);
		BigInteger sumAmountAtDate = transactionDao.sumAmountAtDate(walletId,  beginningOfDay);

		System.out.println(numberOfTransactions);
		System.out.println(date);
		System.out.println(sumAmountAtDate);
		System.out.println(beginningOfDay);
	}

	private void updateWalletBalance(Transaction transaction) {
		Wallet wallet = readById(transaction.getWalletId());
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
}
