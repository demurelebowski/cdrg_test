package org.drg.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.math.BigInteger;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WalletDto {
	@ApiModelProperty(notes = "Wallet ID", example = "33")
	@JsonProperty("id")
	private Integer id;
	@ApiModelProperty(notes = "Balance in cents", example = "10000")
	@JsonProperty("balance")
	private BigInteger balance;
	@ApiModelProperty(notes = "Currency", example = "EUR", required = true)
	@JsonProperty("currency")
	private String currency;
	@ApiModelProperty(notes = "Phone", example = "+180504433678", required = true)
	@JsonProperty("phone")
	private String phone;
	@ApiModelProperty(notes = "First name", example = "John", required = true)
	@JsonProperty("first_name")
	private String firstName;
	@ApiModelProperty(notes = "Middle name", example = "Frank")
	@JsonProperty("middle_name")
	private String middleName;
	@ApiModelProperty(notes = "Last name", example = "Smith", required = true)
	@JsonProperty("last_name")
	private String lastName;
	@JsonProperty("email")
	@Email(message = "Invalid email")
	@Size(max = 100, message = "Email size cannot be more than 100 characters")
	@ApiModelProperty(notes = "Email address", example = "john.smith@example.com", required = true)
	private String email;
	@JsonProperty("flag")
	@ApiModelProperty(notes = "Flag")
	private String Flag;
}
