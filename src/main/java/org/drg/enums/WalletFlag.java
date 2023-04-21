package org.drg.enums;

import java.util.Arrays;

public enum WalletFlag {
	SUSPICIOUS("SUSPICIOUS");
	private final String value;

	WalletFlag(String value) {
		this.value = value;
	}

	public static WalletFlag getByString(String str) {
		return Arrays.stream(WalletFlag.values())
				.filter(e -> e.getValue()
						.equals(str))
				.findAny()
				.orElse(null);
	}

	public String getValue() {
		return value;
	}
}
