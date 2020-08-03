package net.worldticket.client;

import net.worldticket.client.exception.AbsentMoneyException;
import net.worldticket.model.SheepDTO;
import net.worldticket.model.SheepList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class ShepherdRestClient {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private RestTemplate restTemplate;
    @Value( "${server.address.api}" )
    private String serverAddressApi;

    public ShepherdRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SheepDTO orderOneSheep(){
        ResponseEntity<SheepDTO> responseEntity = restTemplate.postForEntity(serverAddressApi + "/shepherd/order/sheep",
                null,
                SheepDTO.class);
        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy HH:mm:ss");

        log.info("you have order one Sheep in " + formatter.format(responseEntity.getBody().getDate()));
        return responseEntity.getBody();
    }

    public String orderSheep(int sheepToOrder) throws AbsentMoneyException {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(serverAddressApi + "/shepherd/order/sheep/" + sheepToOrder,
                null,
                String.class);

        if (responseEntity.getStatusCode().equals(HttpStatus.CREATED)) {
            return responseEntity.getBody();
        }
        throw new AbsentMoneyException(("You Haven`t enough money to buy " + sheepToOrder + " Sheep"));
    }
}
