package com.app.transactionservice.controller;

import com.app.basedomains.dto.Transaction;
import com.app.basedomains.dto.TransactionEvent;
import com.app.transactionservice.kafka.TransactionProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

	private TransactionProducer transactionProducer;

	public TransactionController(TransactionProducer transactionProducer) {
		this.transactionProducer = transactionProducer;
	}

	@PostMapping("/transactions")
	public String placeTransaction(@RequestBody Transaction transaction){

		transaction.setId(UUID.randomUUID().toString());

		TransactionEvent transactionEvent = new TransactionEvent();
		transactionEvent.setStatus("PENDING");
		transactionEvent.setMessage("transaction status is in pending state");
		transactionEvent.setTransaction(transaction);

		transactionProducer.sendMessage(transactionEvent);

		return "Transaction placed successfully ...";
	}
}
