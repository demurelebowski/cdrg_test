package org.drg.dao;

import org.drg.entity.Transaction;
import org.drg.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;

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
	public Integer numberOfTransactions(Integer walletId, LocalDateTime date) {
		return transactionMapper.numberOfTransactions(walletId, date);
	}

	@Override
	public BigInteger sumAmountAtDate(Integer walletId, LocalDateTime date) {
		return transactionMapper.sumAmountAtDate(walletId, date);
	}
}
