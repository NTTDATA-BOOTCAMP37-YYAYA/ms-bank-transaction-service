package com.nttdata.bootcamp.msbanktransaction.domain.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.msbanktransaction.application.incoming.CalculateCommissionUseCase;
import com.nttdata.bootcamp.msbanktransaction.application.incoming.TransactionUseCase;
import com.nttdata.bootcamp.msbanktransaction.application.outgoing.FindBusinessRulesOfProductPort;
import com.nttdata.bootcamp.msbanktransaction.application.outgoing.TransactionPort;
import com.nttdata.bootcamp.msbanktransaction.domain.enums.SwitchValidate;
import com.nttdata.bootcamp.msbanktransaction.domain.enums.TransactionRulesIds;
import com.nttdata.bootcamp.msbanktransaction.domain.model.BankTransaction;
import com.nttdata.bootcamp.msbanktransaction.domain.model.Rule;

import reactor.core.publisher.Mono;

/**.*/
@Service
public class TransactionService implements TransactionUseCase, CalculateCommissionUseCase {

  final  Logger logger = LoggerFactory.getLogger(TransactionService.class);
  
  @Autowired
  private TransactionPort transactionPort;
  


  @Override
  public Mono<BankTransaction> insert(BankTransaction banktransaction) {
    return transactionPort.insert(banktransaction);
    
  }

  @Override
  public Mono<BankTransaction> transactionCommission(BankTransaction transaction, 
                                                 long quantityTransaction, 
                                                 List<Rule> rules) {
    Optional<Rule> ruleAllowedDeposit = FindBusinessRulesOfProductPort.compare(rules,
                                      TransactionRulesIds.ALLOWDEPOSIT.getValue());
    Optional<Rule> ruleMaxMovement = FindBusinessRulesOfProductPort.compare(rules,
                                      TransactionRulesIds.MAXMOVEMENT.getValue());
    Optional<Rule> ruleCommissionMaxMovement = FindBusinessRulesOfProductPort.compare(rules,
                                      TransactionRulesIds.COMMISSIONMAXMOVEMENT.getValue());
    if (ruleAllowedDeposit.isPresent() 
        && ruleAllowedDeposit.get().getRuleValue().equals(SwitchValidate.NO.getValue())) {
      
      Mono.error(new Exception("Account doesn't allow deposits"));
    }
    return  TransactionPort.transactionCommission(ruleMaxMovement.get(),
      ruleCommissionMaxMovement.get(), transaction, quantityTransaction);
  }


}
