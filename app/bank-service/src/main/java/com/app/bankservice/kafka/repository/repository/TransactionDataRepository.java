package com.app.bankservice.kafka.repository.repository;

import com.app.bankservice.kafka.entity.BankTransactionData;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionDataRepository extends JpaRepository<BankTransactionData, String> {

}
