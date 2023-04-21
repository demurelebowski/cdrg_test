package org.drg.enums;

import java.util.Arrays;

public enum TransactionType {
	WITHDRAWAL, DEPOSIT;

	public static TransactionType getByString(String str) {
		return Arrays.stream(TransactionType.values())
				.filter(e -> e.name()
						.equals(str))
				.findAny()
				.orElse(null);
	}

}
