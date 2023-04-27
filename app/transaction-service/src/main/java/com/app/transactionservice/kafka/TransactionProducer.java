package com.app.transactionservice.kafka;

import com.app.basedomains.dto.TransactionEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class TransactionProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionProducer.class);

	private final NewTopic topic;

	private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;

	public TransactionProducer(NewTopic topic,
							   KafkaTemplate<String, TransactionEvent> kafkaTemplate) {
		this.topic = topic;
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(TransactionEvent event){
		LOGGER.info(String.format("Payment Transaction => %s", event.toString()));

		// create Message
		Message<TransactionEvent> message = MessageBuilder
				.withPayload(event)
				.setHeader(KafkaHeaders.TOPIC, topic.name())
				.build();
		kafkaTemplate.send(message);
	}
}
