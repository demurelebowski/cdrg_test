package org.drg.dao;

import org.drg.entity.Wallet;
import org.drg.mapper.WalletMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WalletDaoImpl implements WalletDao {
	private final WalletMapper walletMapper;

	public WalletDaoImpl(@Autowired WalletMapper walletMapper) {
		this.walletMapper = walletMapper;
	}

	@Override
	public Wallet readById(Integer id) {
		return walletMapper.readById(id);
	}

	@Override
	public void create(Wallet wallet) {
		walletMapper.create(wallet);
	}

	@Override
	public void update(Wallet wallet) {
		walletMapper.update(wallet);
	}
}
