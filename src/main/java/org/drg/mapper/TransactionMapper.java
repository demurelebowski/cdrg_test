package org.drg.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.drg.entity.Transaction;

@Mapper
public interface TransactionMapper {
	void create(Transaction transaction);
}
