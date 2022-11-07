package com.nttdata.bootcamp.msbanktransaction.application.incoming;

import java.util.List;

import com.nttdata.bootcamp.msbanktransaction.domain.model.BankTransaction;
import com.nttdata.bootcamp.msbanktransaction.domain.model.Rule;

import reactor.core.publisher.Mono;

/**.*/
public interface CalculateCommissionUseCase {

  Mono<BankTransaction> transactionCommission(BankTransaction transaction, 
                                         long quantityTransaction, List<Rule> rules);
}
