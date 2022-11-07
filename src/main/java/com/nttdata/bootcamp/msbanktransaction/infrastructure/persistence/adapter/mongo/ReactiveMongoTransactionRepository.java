package com.nttdata.bootcamp.msbanktransaction.infrastructure.persistence.adapter.mongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.msbanktransaction.infrastructure.persistence.entity.BankTransactionEntity;


/**.*/
public interface ReactiveMongoTransactionRepository extends ReactiveMongoRepository
                                                            <BankTransactionEntity, String> {

}