package org.drg.enums;

import java.util.Arrays;

public enum TransactionType {
	WITHDRAWAL("WITHDRAWAL"), DEPOSIT("DEPOSIT");
	private final String value;

	TransactionType(String value) {
		this.value = value;
	}

	public static TransactionType getByString(String str) {
		return Arrays.stream(TransactionType.values())
				.filter(e -> e.getValue()
						.equals(str))
				.findAny()
				.orElse(null);
	}

	public String getValue() {
		return value;
	}
}
