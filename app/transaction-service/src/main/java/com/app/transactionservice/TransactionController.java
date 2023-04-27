package com.app.transactionservice;

import com.app.bankservice.entity.BankTransactionData;
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
	public String placeTransaction(@RequestBody BankTransactionData incomingEvent){

		BankTransactionData transactionEvent = new BankTransactionData();
		transactionEvent.setId(UUID.randomUUID().toString());
		transactionEvent.setRecipient(incomingEvent.getRecipient());
		transactionEvent.setSender(incomingEvent.getSender());
		transactionEvent.setAmount(incomingEvent.getAmount());
		transactionEvent.setComments(incomingEvent.getComments());

		transactionProducer.sendMessage(transactionEvent);

		return "Transaction placed successfully ...";
	}
}
