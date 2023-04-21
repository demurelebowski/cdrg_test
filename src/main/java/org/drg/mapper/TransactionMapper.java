package org.drg.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.drg.entity.Transaction;

import java.math.BigInteger;

@Mapper
public interface TransactionMapper {
	void create(Transaction transaction);

	Integer numberOfTransactions(Integer walletId, String date);

	BigInteger sumAmountAtDate(Integer walletId, String date);
}
