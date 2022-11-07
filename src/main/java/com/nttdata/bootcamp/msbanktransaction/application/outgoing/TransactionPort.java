package com.nttdata.bootcamp.msbanktransaction.application.outgoing;

import com.nttdata.bootcamp.msbanktransaction.domain.model.BankTransaction;
import com.nttdata.bootcamp.msbanktransaction.domain.model.Rule;

import reactor.core.publisher.Mono;

/**.*/
public interface TransactionPort {
  
  Mono<BankTransaction> insert(BankTransaction transaction);
  
  /**.*/
  static Mono<BankTransaction> transactionCommission(Rule ruleMaxMovement, 
                                                 Rule ruleCommissionMaxMovement, 
                                                 BankTransaction transaction, 
                                                 long quantityTransaction) {
  long commission = 0;
    if (ruleMaxMovement.getRuleValue().matches("[0-9]+")
        && ruleCommissionMaxMovement.getRuleValue().matches("[0-9]+") 
        && Long.valueOf(quantityTransaction)
        .compareTo(Long.parseLong(ruleMaxMovement.getRuleValue())) >= 0) {
      
      commission = Long.valueOf(ruleCommissionMaxMovement.getRuleValue());
    }
    transaction.setTransactionCommission(commission);
    return Mono.just(transaction);
    
  }
  
}
