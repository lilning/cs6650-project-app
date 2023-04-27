package com.app.transactionservice.controller;

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

	private final TransactionProducer transactionProducer;

	public TransactionController(TransactionProducer transactionProducer) {
		this.transactionProducer = transactionProducer;
	}

	@PostMapping("/transactions")
	public String placeTransaction(@RequestBody TransactionEvent incomingEvent){

		TransactionEvent transactionEvent = new TransactionEvent();
		transactionEvent.setId(UUID.randomUUID().toString());

		transactionEvent.setStatus("PENDING");
		transactionEvent.setMessage("transaction status is in pending state");
		transactionEvent.setRecipient(incomingEvent.getRecipient());
		transactionEvent.setSender(incomingEvent.getSender());
		transactionEvent.setAmount(incomingEvent.getAmount());
		transactionEvent.setComments(incomingEvent.getComments());

		transactionProducer.sendMessage(transactionEvent);

		return "Transaction placed successfully ...";
	}
}
