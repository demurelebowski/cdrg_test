package org.drg.enums;

import java.util.Arrays;

public enum TransactionFlag {
	SUSPICIOUS;

	public static TransactionFlag getByString(String str) {
		return Arrays.stream(TransactionFlag.values())
				.filter(e -> e.name()
						.equals(str))
				.findAny()
				.orElse(null);
	}

}
