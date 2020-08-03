package net.worldticket.rest;

import net.worldticket.dto.CurrentBalanceDto;
import net.worldticket.service.CurrentBalanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balance")
public class CurrentBalanceRestController {

    private CurrentBalanceService currentBalanceService;

    public CurrentBalanceRestController(CurrentBalanceService currentBalanceService) {
        this.currentBalanceService = currentBalanceService;
    }

    @GetMapping
    public ResponseEntity<CurrentBalanceDto> getCurrentBalance(){
        return new ResponseEntity<>(currentBalanceService.getCurrentBalance(), HttpStatus.OK);
    }

}
