package com.nttdata.bootcamp.msbanktransaction.infrastructure.persistence.adapter.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nttdata.bootcamp.msbanktransaction.application.outgoing.TransactionPort;
import com.nttdata.bootcamp.msbanktransaction.domain.model.BankTransaction;
import com.nttdata.bootcamp.msbanktransaction.infrastructure.persistence.entity.BankTransactionEntity;

import reactor.core.publisher.Mono;

/**.*/
@Component
public class TransactionRepositoryAdapter implements TransactionPort {

  @Autowired
  private ReactiveMongoTransactionRepository reactiveMongoDB;

  @Override
  public Mono<BankTransaction> insert(BankTransaction bankTransaction) {
    return reactiveMongoDB.insert(BankTransactionEntity.toBankTransactionEntity(bankTransaction))
        .map(BankTransactionEntity::toBankTransaction);
  }



}
