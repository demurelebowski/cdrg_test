package org.drg.enums;

import java.util.Arrays;

public enum Currency {
	USD, EUR;

	public static Currency getByString(String str) {
		return Arrays.stream(Currency.values())
				.filter(e -> e.name()
						.equals(str))
				.findAny()
				.orElse(null);
	}

}
