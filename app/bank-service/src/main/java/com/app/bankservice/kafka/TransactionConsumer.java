package com.app.bankservice.kafka;

import com.app.bankservice.kafka.entity.BankTransactionData;
import com.app.bankservice.kafka.repository.repository.TransactionDataRepository;
import com.app.bankservice.kafka.repository.repository.UserDataRepository;
import com.app.basedomains.dto.TransactionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class TransactionConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionConsumer.class);
	private final TransactionDataRepository transactionRepository;
	private final UserDataRepository userRepository;

	public TransactionConsumer(TransactionDataRepository transactionRepository, UserDataRepository userRepository) {
		this.transactionRepository = transactionRepository;
		this.userRepository = userRepository;
	}


	@KafkaListener(
			topics = "${spring.kafka.topic.name}"
			,groupId = "${spring.kafka.consumer.group-id}"
	)


	public void consume(TransactionEvent event){
		LOGGER.info(String.format("Transaction event received in bank service => %s",
				event.toString()));


		BankTransactionData bankTransactionData = new BankTransactionData();
		bankTransactionData.setId(event.getId());
		bankTransactionData.setSender(event.getSender());
		bankTransactionData.setComments(event.getComments());
		bankTransactionData.setAmount(event.getAmount());
		bankTransactionData.setRecipient(event.getRecipient());

		//Checking for sufficient balance
		if (sufficientBalance(bankTransactionData)) {
			//updating balance for users in transaction
			setSenderBalance(bankTransactionData);
			setRecipientBalance(bankTransactionData);

			//writing bank transaction data to transaction table
			transactionRepository.save(bankTransactionData);
		}

		//We then save the banktransaction entity to the MySQL database and it populates the table
		/* TODO: save the transaction event into the database */


	}

	//Checks that sender username balance has sufficient funds
	public boolean sufficientBalance(BankTransactionData bankTransactionData) {
		LOGGER.info("Checking for sufficient sender balance...");
		String sender = bankTransactionData.getSender();
		Double balance = bankTransactionData.getAmount();
		try {
			if (userRepository.findUserBalance(sender) >= balance) {
				LOGGER.info("Sufficient funds for user: " + sender);
				return true;
			}
			LOGGER.info("Warning! Insufficient funds for user: " + sender);
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("ERROR: could not check balance for " + sender);
		}
		return false;
	}

	//Sets balance for sender
	public void setSenderBalance(BankTransactionData bankTransactionData) {
		LOGGER.info("Calculating sender balance...");
		Double amount = bankTransactionData.getAmount();
		String sender = bankTransactionData.getSender();
		try {
			userRepository.setSenderBalance(amount, sender);
			LOGGER.info("Balance successfully calculated for " + sender + ". Balance is now " + userRepository.findUserBalance(sender));
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("ERROR: balance was not calculated for " + sender);
		}

	}

	//Sets balance for recipient
	public void setRecipientBalance(BankTransactionData bankTransactionData) {
		LOGGER.info("Calculating recipient balance...");
		Double amount = bankTransactionData.getAmount();
		String recipient = bankTransactionData.getRecipient();
		try {
			userRepository.setSenderBalance(amount, recipient);
			LOGGER.info("Balance successfully calculated for " + recipient + ". Balance is now " + userRepository.findUserBalance(recipient));
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("ERROR: balance was not calculated for " + recipient);
		}
	}

}
