package org.drg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WalletEventDto {
	private Integer id;
	private Integer walletId;
	private String date;
	private String walletEventType;
}
