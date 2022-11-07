package com.nttdata.bootcamp.msbanktransaction.application.outgoing;

import com.nttdata.bootcamp.msbanktransaction.domain.model.Rule;
import java.util.List;
import java.util.Optional;
import reactor.core.publisher.Mono;

/**.*/
public interface FindBusinessRulesOfProductPort {

  public  Mono<List<Rule>> findBusinessRulesOfProduct(String productId,
                                                      String customerTypeId, 
                                                      String customerCategoryId,
                                                      String actionId);
  
  static Optional<Rule> compare(List<Rule> rules, String ruleValueEnum) {
    return rules.stream().filter(rule -> rule.getRuleId()
        .equals(ruleValueEnum)).findFirst();
  }
}
