package com.app.bankservice.kafka.repository.repository;


import com.app.bankservice.kafka.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserDataRepository extends JpaRepository<UserData, String> {

  //Queries the UserData table to find the balance for associated username
  @Query("SELECT balance FROM UserData WHERE username = :username")
  Double findUserBalance(@Param("username") String username);

  //Updates the balance for the associated username in the UserData table
  @Modifying
  @Query("UPDATE UserData u SET u.balance = u.balance - :amount WHERE u.username = :username")
  void setSenderBalance(@Param("amount") Double amount, @Param("username") String username);

  @Modifying
  @Query("UPDATE UserData u SET u.balance = u.balance - :amount WHERE u.username = :username")
  void setRecipientBalance(@Param("amount") Double amount, @Param("username") String username);


}
