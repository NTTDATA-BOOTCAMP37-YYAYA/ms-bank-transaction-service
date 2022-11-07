package com.nttdata.bootcamp.msbanktransaction.infrastructure.persistence.entity;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nttdata.bootcamp.msbanktransaction.domain.model.BankTransaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "BankTransaction")
public class BankTransactionEntity {

  @Id
  private String transactionId;
  private String transactionTypeId;
  private double transactionAmount;
  private String transactionCurrency;
  private long   transactionCommission;
  private String transactionNumberOrigen;
  private String accountSource;
  private String accountDestiny;
  private double transactionExchangeRate;

  /**.*/
  public static BankTransaction toBankTransaction(BankTransactionEntity bankTransactionEntity) {
	BankTransaction bankTransaction = new BankTransaction();
    BeanUtils.copyProperties(bankTransactionEntity, bankTransaction);
    return bankTransaction;
  }

  /**.*/
  public static BankTransactionEntity toBankTransactionEntity(BankTransaction bankTransaction) {
    BankTransactionEntity bankTransactionEntity = new BankTransactionEntity();
    BeanUtils.copyProperties(bankTransaction, bankTransactionEntity);
    return bankTransactionEntity;
  }
}
