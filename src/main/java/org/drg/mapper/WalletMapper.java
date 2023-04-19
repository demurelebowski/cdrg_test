package org.drg.mapper;

import org.drg.entity.Wallet;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WalletMapper {
	void create(Wallet wallet);

	Wallet readById(Integer id);
}
