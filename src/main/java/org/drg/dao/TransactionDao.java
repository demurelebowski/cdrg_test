package org.drg.dao;

import org.drg.entity.Transaction;

import java.math.BigInteger;

public interface TransactionDao {
	void create(Transaction transaction);

	Integer numberOfTransactions(Integer walletId, String date);

	BigInteger sumAmountAtDate(Integer walletId, String date);
}
