package org.drg.dao;

import org.drg.entity.Wallet;
import org.drg.enums.Currency;

public interface WalletDao {
	Wallet readById(Integer id);

	void create(Wallet wallet);

	void update(Wallet wallet);

	Wallet readByPhoneOrEmail(String phone, String email, Currency currency);
}
