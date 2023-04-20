package org.drg.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.drg.entity.Transaction;

import java.time.LocalDateTime;

@Mapper
public interface TransactionMapper {
	void create(Transaction transaction);

	Integer numberOfTransactions(Integer walletId, LocalDateTime date);
}
