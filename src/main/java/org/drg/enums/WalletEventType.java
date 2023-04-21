package org.drg.enums;

import java.util.Arrays;

public enum WalletEventType {
	BLOCKED, UNBLOCKED;

	public static WalletEventType getByString(String str) {
		return Arrays.stream(WalletEventType.values())
				.filter(e -> e.name()
						.equals(str))
				.findAny()
				.orElse(null);
	}

}
