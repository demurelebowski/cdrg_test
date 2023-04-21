package org.drg.dao;

import org.drg.entity.WalletEvent;
import org.drg.mapper.WalletEventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WalletEventDaoImpl implements WalletEventDao {
	private final WalletEventMapper walletEventMapper;

	public WalletEventDaoImpl(@Autowired WalletEventMapper walletEventMapper) {
		this.walletEventMapper = walletEventMapper;
	}

	@Override
	public void create(WalletEvent walletEvent) {
		walletEventMapper.create(walletEvent);
	}

	@Override
	public WalletEvent readLastEventByDate(Integer walletId, String startDate, String endDate) {
		return walletEventMapper.readLastEventByDate(walletId, startDate, endDate);
	}
}
