package com.nttdata.bootcamp.msbanktransaction.application.incoming;

import com.nttdata.bootcamp.msbanktransaction.domain.model.BankTransaction;

import reactor.core.publisher.Mono;

/**.*/
public interface TransactionUseCase {
  
  Mono<BankTransaction> insert(BankTransaction transaction);
  
}
