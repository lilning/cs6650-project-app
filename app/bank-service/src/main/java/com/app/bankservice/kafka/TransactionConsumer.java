package com.app.bankservice.kafka;

import com.app.basedomains.dto.TransactionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionConsumer.class);

	@KafkaListener(
			topics = "${spring.kafka.topic.name}"
			,groupId = "${spring.kafka.consumer.group-id}"
	)
	public void consume(TransactionEvent event){
		LOGGER.info(String.format("Transaction event received in bank service => %s",
				event.toString()));

		/* TODO: If payment out, validate if account balance > amt */
		// update transactionEvent status and message, populate the info as response for POST
		/* TODO: save the transaction event into the database */
		/* TODO: update account balance */
	}
}