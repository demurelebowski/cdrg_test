package org.drg.enums;

import java.util.Arrays;

public enum TransactionStatus {
	SUCCESSFUL, CANCELED;

	public static TransactionStatus getByString(String str) {
		return Arrays.stream(TransactionStatus.values())
				.filter(e -> e.name()
						.equals(str))
				.findAny()
				.orElse(null);
	}

}
