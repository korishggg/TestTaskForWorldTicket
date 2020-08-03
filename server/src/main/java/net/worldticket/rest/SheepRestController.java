package net.worldticket.rest;

import net.worldticket.dto.SheepStatusesDto;
import net.worldticket.service.SheepService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sheep")
public class SheepRestController {
    private SheepService sheepService;

    public SheepRestController(SheepService sheepService) {
        this.sheepService = sheepService;
    }

    @GetMapping
    public ResponseEntity<SheepStatusesDto> getSheepStatusesDto(){
        return new ResponseEntity<>(sheepService.getSheepStatusesDto(), HttpStatus.OK);
    }
}
