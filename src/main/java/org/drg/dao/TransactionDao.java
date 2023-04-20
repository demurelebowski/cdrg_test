package org.drg.dao;

import org.drg.entity.Transaction;

import java.math.BigInteger;
import java.time.LocalDateTime;

public interface TransactionDao {
	void create(Transaction transaction);

	Integer numberOfTransactions(Integer walletId, LocalDateTime date);

	BigInteger sumAmountAtDate(Integer walletId, LocalDateTime date);
}
