package com.app.basedomains.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
	private String id;
	private double amount;
	private String sender;
	private String recipient;
	private String comments;
}
