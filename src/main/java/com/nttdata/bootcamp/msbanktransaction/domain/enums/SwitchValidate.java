package com.nttdata.bootcamp.msbanktransaction.domain.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**.*/
@AllArgsConstructor
@NoArgsConstructor
public enum SwitchValidate {

  YES("S"),
  NO("N");
  
  private String value;

  public String getValue() {
    return value;
  }



}
