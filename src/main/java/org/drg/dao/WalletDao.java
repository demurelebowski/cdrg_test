package org.drg.dao;

import org.drg.entity.Wallet;

public interface WalletDao {
	Wallet readById(Integer id);
}
