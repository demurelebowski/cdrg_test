package org.drg.dao;

import org.drg.entity.WalletEvent;

public interface WalletEventDao {
	void create(WalletEvent walletEvent);

	WalletEvent readLastEventByDate(Integer walletId, String startDate, String endDate);
}
