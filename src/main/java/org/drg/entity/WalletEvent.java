package org.drg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.drg.enums.WalletEventType;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WalletEvent {
	private Integer id;
	private Integer walletId;
	private LocalDateTime date;
	private WalletEventType walletEventType;
}
