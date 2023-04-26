package com.app.bankservice.kafka.repository.repository;

import com.app.bankservice.kafka.entity.BankTransactionData;
import com.app.bankservice.kafka.entity.UserData;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, String> {
}
