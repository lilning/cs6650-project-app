package com.app.bankservice.repository;

import com.app.bankservice.entity.BankTransactionData;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionDataRepository extends JpaRepository<BankTransactionData, String> {

}
