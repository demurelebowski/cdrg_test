package org.drg.enums;

import java.util.Arrays;

public enum TransactionFlag {
	SUSPICIOUS("SUSPICIOUS");
	private final String value;

	TransactionFlag(String value) {
		this.value = value;
	}

	public static TransactionFlag getByString(String str) {
		return Arrays.stream(TransactionFlag.values())
				.filter(e -> e.getValue()
						.equals(str))
				.findAny()
				.orElse(null);
	}

	public String getValue() {
		return value;
	}
}
