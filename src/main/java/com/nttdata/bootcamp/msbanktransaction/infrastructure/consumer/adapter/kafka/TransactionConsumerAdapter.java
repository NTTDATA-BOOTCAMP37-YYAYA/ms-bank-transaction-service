package com.nttdata.bootcamp.msbanktransaction.infrastructure.consumer.adapter.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.nttdata.bootcamp.msbanktransaction.application.incoming.TransactionUseCase;
import com.nttdata.bootcamp.msbanktransaction.application.outgoing.SendTransactionBootCoinPort;
import com.nttdata.bootcamp.msbanktransaction.domain.model.BankTransaction;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransactionConsumerAdapter {

  final  Logger logger = LoggerFactory.getLogger(TransactionConsumerAdapter.class);


  @Autowired
  private TransactionUseCase  transactionUseCase;
  
  @Autowired
  private SendTransactionBootCoinPort sendTransactionBootCoinPort;

  
  @KafkaListener(topics = "${kafka.topic.bank.transaction.bootcoin-create.name}")
  public void listenerCreate(@Payload BankTransaction bankTransaction) {
	  transactionUseCase.insert(bankTransaction).block();
	  this.sendTransactionBootCoinPort.sendTransactionBootCoin(bankTransaction);
  }

  
  /**.*/
  public BankTransaction sendWalletBalance(BankTransaction bankTransaction) {
    if (bankTransaction != null) {
      logger.info("Send  Bank transaction Bootcoin {} ", bankTransaction);
      sendTransactionBootCoinPort.sendTransactionBootCoin(bankTransaction);
    }
    return bankTransaction;
  }
}