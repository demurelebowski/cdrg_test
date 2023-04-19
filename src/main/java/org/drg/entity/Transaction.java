package org.drg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.drg.enums.Currency;
import org.drg.enums.TransactionType;

import java.math.BigInteger;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {
	private BigInteger id;
	private BigInteger walletId;
	private Date time;
	private TransactionType transactionType;
	private BigInteger amount;
	private Currency currency;
	private String comment;
}
