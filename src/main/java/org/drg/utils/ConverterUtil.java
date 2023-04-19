package org.drg.utils;

import org.apache.commons.lang3.EnumUtils;
import org.drg.dto.TransactionDto;
import org.drg.dto.WalletDto;
import org.drg.entity.Transaction;
import org.drg.entity.Wallet;
import org.drg.enums.Currency;
import org.drg.enums.TransactionType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class ConverterUtil {
	public static Wallet convertToWallet(WalletDto walletDto) {
		if (Objects.isNull(walletDto)) {
			return null;
		}
		return Wallet.builder()
				.phone(walletDto.getPhone())
				.id(walletDto.getId())
				.email(walletDto.getEmail())
				.balance(walletDto.getBalance())
				.currency(EnumUtils.getEnumIgnoreCase(Currency.class, walletDto.getCurrency()))
				.firstName(walletDto.getFirstName())
				.middleName(walletDto.getMiddleName())
				.lastName(walletDto.getLastName())
				.flag(walletDto.getFlag())
				.build();
	}

	public static WalletDto convertToWalletDto(Wallet wallet) {
		if (Objects.isNull(wallet)) {
			return null;
		}
		return WalletDto.builder()
				.id(wallet.getId())
				.email(wallet.getEmail())
				.phone(wallet.getPhone())
				.currency(getStringFromEnum(wallet.getCurrency()))
				.balance(wallet.getBalance())
				.firstName(wallet.getFirstName())
				.middleName(wallet.getMiddleName())
				.lastName(wallet.getLastName())
				.Flag(wallet.getFlag())
				.build();
	}

	public static Transaction convertToTransaction(TransactionDto transactionDto) {
		if (Objects.isNull(transactionDto)) {
			return null;
		}
		return Transaction.builder()
				.id(transactionDto.getId())
				.amount(transactionDto.getAmount())
				.transactionType(EnumUtils.getEnumIgnoreCase(TransactionType.class, transactionDto.getTransactionType()))
				.walletId(transactionDto.getWalletId())
				.currency(EnumUtils.getEnumIgnoreCase(Currency.class, transactionDto.getCurrency()))
				.time(dateFromString(transactionDto.getTime()))
				.comment(transactionDto.getComment())
				.build();
	}

	public static TransactionDto convertToTransactionDto(Transaction transaction) {
		if (Objects.isNull(transaction)) {
			return null;
		}

		return TransactionDto.builder()
				.amount(transaction.getAmount())
				.id(transaction.getId())
				.walletId(transaction.getWalletId())
				.transactionType(getStringFromEnum(transaction.getTransactionType()))
				.currency(getStringFromEnum(transaction.getCurrency()))
				.time(getStringFromDate(transaction.getTime()))
				.comment(transaction.getComment())
				.build();
	}

	private static String getStringFromEnum(Enum obj) {
		return obj == null ? null : obj.name();
	}

	private static String getStringFromDate(Date date) {
		if (Objects.isNull(date)) {
			return null;
		}
		return date.toString();
	}

	private static Date dateFromString(String str) {
		if (Objects.isNull(str)) {
			return null;
		}
		try {
			return new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss", Locale.ENGLISH).parse(str);
		} catch (Exception e) {
			return null;
		}
	}
}
