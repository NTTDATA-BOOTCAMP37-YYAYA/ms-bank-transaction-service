package com.nttdata.bootcamp.msbanktransaction.domain.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**.*/
@AllArgsConstructor
@NoArgsConstructor
public enum TransactionRulesIds {

  MAXMOVEMENT("1"),
  COMMISSIONMAXMOVEMENT("2"),
  ALLOWDEPOSIT("3"),
  ALLOWITHDRAWAL("3");
  
  private String value;
   
  public String getValue() {
    return value;
  }


}
