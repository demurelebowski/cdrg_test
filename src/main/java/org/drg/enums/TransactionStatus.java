package org.drg.enums;

import java.util.Arrays;

public enum TransactionStatus {
	SUCCESSFUL("SUCCESSFUL"), CANCELED("CANCELED");
	private final String value;

	TransactionStatus(String value) {
		this.value = value;
	}

	public static TransactionStatus getByString(String str) {
		return Arrays.stream(TransactionStatus.values())
				.filter(e -> e.getValue()
						.equals(str))
				.findAny()
				.orElse(null);
	}

	public String getValue() {
		return value;
	}
}
