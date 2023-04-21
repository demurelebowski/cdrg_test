package org.drg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.drg.enums.Currency;
import org.drg.enums.WalletFlag;

import java.math.BigInteger;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Wallet {
	private Integer id;
	private BigInteger balance;
	private Currency currency;
	private String phone;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private WalletFlag flag;
}
