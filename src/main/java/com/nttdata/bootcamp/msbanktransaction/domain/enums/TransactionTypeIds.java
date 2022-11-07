package com.nttdata.bootcamp.msbanktransaction.domain.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**.*/
@AllArgsConstructor
@NoArgsConstructor
public enum TransactionTypeIds {

  DEPOSIT("1"),
  WITHDRAWAL("2");
  
  private String value;

  public String getValue() {
    return value;
  }



}
