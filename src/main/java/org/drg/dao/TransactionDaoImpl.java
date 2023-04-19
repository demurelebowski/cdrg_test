package org.drg.dao;

import org.drg.entity.Transaction;
import org.drg.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
