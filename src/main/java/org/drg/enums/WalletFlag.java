package org.drg.enums;

import java.util.Arrays;

public enum WalletFlag {
	SUSPICIOUS;

	public static WalletFlag getByString(String str) {
		return Arrays.stream(WalletFlag.values())
				.filter(e -> e.name()
						.equals(str))
				.findAny()
				.orElse(null);
	}

}
