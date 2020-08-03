package net.worldticket.rest;

import net.worldticket.dto.SheepDTO;
import net.worldticket.dto.SheepList;
import net.worldticket.exception.AbsentMoneyException;
import net.worldticket.service.ShepherdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/shepherd")
public class ShepherdResource {
    private ShepherdService service;

    @Autowired
    public ShepherdResource(ShepherdService service) {
        this.service = service;
    }

    @PostMapping("/order/sheep")
    @ResponseBody
    public ResponseEntity<?> orderSheep(){
        SheepDTO sheep;
        try {
            sheep = service.buyOneSheep();
        } catch (AbsentMoneyException exception) {
            return new ResponseEntity<>(exception.getNotification(), HttpStatus.OK);
        }
        return new ResponseEntity<>(sheep, HttpStatus.CREATED);
    }

    @PostMapping("/order/sheep/{amount}")
    @ResponseBody
    public ResponseEntity<?> orderSheep(@PathVariable String amount){
        try {
            service.buySheep(Integer.parseInt(amount));
        } catch (AbsentMoneyException exception) {
            return new ResponseEntity<>(exception.getNotification(), HttpStatus.OK);
        }
        return new ResponseEntity<>(amount + " sheep been bought", HttpStatus.CREATED);

    }
}