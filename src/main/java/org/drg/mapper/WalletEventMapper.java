package org.drg.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.drg.entity.WalletEvent;

@Mapper
public interface WalletEventMapper {
	void create(WalletEvent walletEvent);

	WalletEvent readLastEventByDate(Integer walletId, String startDate, String endDate);
}
