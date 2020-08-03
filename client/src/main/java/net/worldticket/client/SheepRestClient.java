package net.worldticket.client;

import net.worldticket.model.SheepStatusesDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SheepRestClient {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private RestTemplate restTemplate;
    @Value( "${server.address.api}" )
    private String serverAddressApi;

    public SheepRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public SheepStatusesDto getSheepStatuses(){

        String endpoint = serverAddressApi + "/sheep";

        ResponseEntity<SheepStatusesDto> responseEntity =restTemplate.getForEntity(endpoint, SheepStatusesDto.class);
        log.info("Fetched data form " +
                endpoint +
                " healthy sheep =" +
                responseEntity.getBody().getNumberOfHealthySheep() +
                " dead sheep =" +
                responseEntity.getBody().getNumberOfDeadSheep());

        return responseEntity.getBody();
    }

}
