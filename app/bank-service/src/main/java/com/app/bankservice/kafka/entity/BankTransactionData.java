package com.app.bankservice.kafka.entity;


import com.fasterxml.jackson.annotation.JsonAnyGetter;

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
  private Long id;
  private String operation;
  private Long amount;
  private Long sender;
  private Long recipient;
  private String comments;

}
