package com.app.basedomains.dto;

import com.app.bankservice.kafka.entity.BankTransactionData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEvent {
	private String message;
	private String status;
	private BankTransactionData transaction;
}
