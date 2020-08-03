package net.worldticket.client;

import net.worldticket.model.CurrentBalanceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrentBalanceRestClient {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private RestTemplate restTemplate;
    @Value( "${server.address.api}" )
    private String serverAddressApi;

    public CurrentBalanceRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CurrentBalanceDto getCurrentBalance(){
        String endpoint = serverAddressApi +"/balance";
        ResponseEntity<CurrentBalanceDto> responseEntity = restTemplate.getForEntity(endpoint, CurrentBalanceDto.class);
        log.info("Fetched data from " + endpoint + " and current balance is = " +  responseEntity.getBody().getBalance() );

        return responseEntity.getBody();
    }



}
