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
public class WalletDto {
	@ApiModelProperty(notes = "Wallet ID", example = "3", required = true)
	@JsonProperty("id")
	private BigInteger id;

	@ApiModelProperty(notes = "Balance", example = "100", required = true)
	@JsonProperty("balance")
	private BigInteger balance;

	private Integer phone;

	private String firstName;

	private String middleName;

	private String lastName;

	private String email;

	private String Flag;
}
