package com.nttdata.bootcamp.msbanktransaction.infrastructure.web.rest.adapter.api;

import com.nttdata.bootcamp.msbanktransaction.application.outgoing.FindBusinessRulesOfProductPort;
import com.nttdata.bootcamp.msbanktransaction.domain.model.Rule;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**.*/
@Component
public class ProductAdapter implements FindBusinessRulesOfProductPort {

  final  Logger logger = LoggerFactory.getLogger(ProductAdapter.class);

  @Value("${client.msbankproduct.url}")
  private String url;

  private WebClient client = WebClient.create(url);


  @Override
  @CircuitBreaker(name = "", fallbackMethod = "findBusinessRulesOfProductAlternative")
  public  Mono<List<Rule>> findBusinessRulesOfProduct(String productId, String customerTypeId,
      String customerCategoryId, String actionId) {
    
    Mono<List<Rule>> response = client.get()
        .uri(url
            .concat("/businessRules/{productId}/{customerTypeId}/{customerCategoryId}/{actionId}"),
            productId,
            customerTypeId,
            customerCategoryId,
            actionId)
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError, clientResponse -> 
        Mono.error(new Exception("Error 400 findBusinessRulesOfProduct")))
        .onStatus(HttpStatus::is5xxServerError, clientResponse ->  
        Mono.error(new Exception("Error 500 findBusinessRulesOfProduct")))
        .bodyToFlux(Rule.class)
        .collectList();
    return response;

  }
  
  public Mono<List<Rule>> findBusinessRulesOfProductAlternative(String productId,
                                                                String customerTypeId,
      String customerCategoryId, String actionId, Exception e) {
    //TODO
    return Mono.error(new Exception("Error on findBusinessRulesOfProduct"));
  }
  

}
