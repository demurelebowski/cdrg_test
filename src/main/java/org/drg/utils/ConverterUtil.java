package org.drg.utils;

import org.drg.dto.WalletDto;
import org.drg.entity.Wallet;

import java.time.format.DateTimeFormatter;

public class ConverterUtil {
	static final private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	static final private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static Wallet convertToWallet(WalletDto walletDto) {
		return new Wallet();
	}

	public static WalletDto convertToWalletDto(Wallet reservation) {
		return new WalletDto();
	}
}
