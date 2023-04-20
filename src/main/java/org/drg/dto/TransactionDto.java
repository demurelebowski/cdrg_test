package org.drg.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDto {
	@ApiModelProperty(notes = "Transaction ID", example = "33")
	@JsonProperty("id")
	private Integer id;
	@ApiModelProperty(notes = "Wallet ID", example = "2", required = true)
	@JsonProperty("wallet_id")
	private Integer walletId;
	@JsonProperty("date")
	@ApiModelProperty(notes = "Date and time when record was created", example = "2022-12-03 16:26:39")
	private String date;
	@JsonProperty("transaction_type")
	@ApiModelProperty(notes = "Transaction type", example = "WITHDRAWAL or DEPOSIT", required = true)
	private String transactionType;
	@ApiModelProperty(notes = "Amount in cents", example = "4566", required = true)
	@JsonProperty("amount")
	private BigInteger amount;
	@ApiModelProperty(notes = "Currency", example = "EUR", required = true)
	@JsonProperty("currency")
	private String currency;
	@JsonProperty("comment")
	@ApiModelProperty(notes = "Comment related to transaction")
	private String comment;
}
