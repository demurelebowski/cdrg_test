package org.drg.enums;

import java.util.Arrays;

public enum Currency {
	USD("USD"), EUR("EUR");
	private final String value;

	Currency(String value) {
		this.value = value;
	}

	public static Currency getByString(String str) {
		return Arrays.stream(Currency.values())
				.filter(e -> e.getValue()
						.equals(str))
				.findAny()
				.orElse(null);
	}

	public String getValue() {
		return value;
	}
}
