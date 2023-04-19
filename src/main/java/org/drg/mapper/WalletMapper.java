package org.drg.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.drg.entity.Wallet;

@Mapper
public interface WalletMapper {
	void create(Wallet wallet);

	Wallet readById(Integer id);

	void update(Wallet wallet);
}
