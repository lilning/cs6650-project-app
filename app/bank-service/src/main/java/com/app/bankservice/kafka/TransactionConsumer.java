package com.app.bankservice.kafka;

import com.app.bankservice.kafka.entity.BankTransactionData;
import com.app.bankservice.kafka.repository.repository.TransactionDataRepository;
import com.app.basedomains.dto.TransactionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionConsumer.class);
	private TransactionDataRepository dataRepository;

	public TransactionConsumer(TransactionDataRepository dataRepository) {
		this.dataRepository = dataRepository;
	}

	@KafkaListener(
			topics = "${spring.kafka.topic.name}"
			,groupId = "${spring.kafka.consumer.group-id}"
	)

	//The TransactionEvent will most likely be replaced with a custom event to fit our bank transaction properties
	public void consume(TransactionEvent event){
		LOGGER.info(String.format("Transaction event received in bank service => %s",
				event.toString()));

		//Create BankTransactionData object so we can then set the data from the TransactionEvent that is being consumed.
		//Once we have the structure for the TransactionEvent we can actually parse it/get the individual
		// data points such as sender, receiver, amount (basically all BankTransactionData properties)
		//Setter code is place holder for now
		BankTransactionData bankTransactionData = new BankTransactionData();
		bankTransactionData.setId(event.getTransaction().getId());
		bankTransactionData.setSender(event.getTransaction().getSender());
		bankTransactionData.setComments(event.getTransaction().getComments());
		bankTransactionData.setAmount(event.getTransaction().getAmount());
		bankTransactionData.setRecipient(event.getTransaction().getRecipient());

		//We then save the banktransaction entity to the MySQL database and it populates the table
		/* TODO: save the transaction event into the database */
		dataRepository.save(bankTransactionData);

		/* TODO: If payment out, validate if account balance > amt */
		// update transactionEvent status and message, populate the info as response for POST

		/* TODO: update account balance */
	}
}
