package com.app.bankservice.kafka.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class BankTransactionData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;
  private double amount;
  private String sender;
  private String recipient;
  private String comments;
}
