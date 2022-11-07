package com.nttdata.bootcamp.msbanktransaction.application.outgoing;

import com.nttdata.bootcamp.msbanktransaction.domain.model.BankTransaction;

public interface SendTransactionBootCoinPort {
	
	void sendTransactionBootCoin(BankTransaction bankTransaction);

}
