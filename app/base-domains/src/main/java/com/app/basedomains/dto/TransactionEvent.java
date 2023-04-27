package com.app.basedomains.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionEvent {
	private String message;
	private String status;
	private String id;
	private double amount;
	private String sender;
	private String recipient;
	private String comments;
}
