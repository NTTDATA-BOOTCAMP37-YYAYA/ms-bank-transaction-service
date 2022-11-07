package com.nttdata.bootcamp.msbanktransaction.infrastructure.producer.adapter.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.nttdata.bootcamp.msbanktransaction.application.outgoing.SendTransactionBootCoinPort;
import com.nttdata.bootcamp.msbanktransaction.domain.model.BankTransaction;

import lombok.RequiredArgsConstructor;

/**.*/
@Component
@RequiredArgsConstructor
public class TransactionBootCoinProducerAdapter implements SendTransactionBootCoinPort {
  
  final  Logger logger = LoggerFactory.getLogger(TransactionBootCoinProducerAdapter.class);
  
  @Value("${kafka.topic.bootcoin.transaction.update.name}")
  private String topic;

  private  final KafkaTemplate<String, BankTransaction> kafkaTemplate;
  
  @Override
  public void sendTransactionBootCoin(BankTransaction bankTransaction) {
    
    ListenableFuture<SendResult<String, BankTransaction>> future = kafkaTemplate.send(this.topic, bankTransaction);
    
    future.addCallback(new ListenableFutureCallback<SendResult<String, BankTransaction>>() {

      @Override
      public void onSuccess(SendResult<String, BankTransaction> result) {
        logger.info("Message {} has been sent", result);
      }

      @Override
      public void onFailure(Throwable ex) {
        logger.error("Something went wrong with the bank transaction");
        
      }

    });
  }

  

}
