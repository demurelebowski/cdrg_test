package org.drg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.drg.enums.Currency;
import org.drg.enums.TransactionFlag;
import org.drg.enums.TransactionType;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {
	private Integer id;
	private Integer walletId;
	private LocalDateTime date;
	private TransactionType transactionType;
	private BigInteger amount;
	private Currency currency;
	private String comment;
	private TransactionFlag flag;
}
