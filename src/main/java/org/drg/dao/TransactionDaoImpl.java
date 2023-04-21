package org.drg.dao;

import org.drg.entity.Transaction;
import org.drg.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class TransactionDaoImpl implements TransactionDao {
	private final TransactionMapper transactionMapper;

	public TransactionDaoImpl(@Autowired TransactionMapper transactionMapper) {
		this.transactionMapper = transactionMapper;
	}

	@Override
	public void create(Transaction transaction) {
		transactionMapper.create(transaction);
	}

	@Override
	public Integer numberOfTransactions(Integer walletId, String date) {
		return transactionMapper.numberOfTransactions(walletId, date);
	}

	@Override
	public BigInteger sumAmountAtDate(Integer walletId, String date) {
		return transactionMapper.sumAmountAtDate(walletId, date);
	}
}
