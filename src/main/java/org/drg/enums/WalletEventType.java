package org.drg.enums;

import java.util.Arrays;

public enum WalletEventType {
	BLOCKED("BLOCKED"), UNBLOCKED("UNBLOCKED");
	private final String value;

	WalletEventType(String value) {
		this.value = value;
	}

	public static WalletEventType getByString(String str) {
		return Arrays.stream(WalletEventType.values())
				.filter(e -> e.getValue()
						.equals(str))
				.findAny()
				.orElse(null);
	}

	public String getValue() {
		return value;
	}
}
