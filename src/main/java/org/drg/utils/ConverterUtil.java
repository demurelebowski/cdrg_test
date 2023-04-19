package org.drg.utils;

import org.drg.dto.WalletDto;
import org.drg.enums.Currency;
import org.drg.entity.Wallet;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.apache.commons.lang3.EnumUtils;

public class ConverterUtil {
	static final private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	static final private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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

	private static String getStringFromEnum(Enum obj) {
		return obj == null ? null : obj.name();
	}
}
