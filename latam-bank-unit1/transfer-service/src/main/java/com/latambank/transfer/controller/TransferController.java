package com.latambank.transfer.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    private final RestTemplate rest = new RestTemplate();

    @PostMapping
    public String transfer(@RequestParam Long fromAcc, @RequestParam Long toAcc, @RequestParam Double amount){
        String debit = "http://localhost:8082/accounts/"+fromAcc+"/debit?amount="+amount;
        String credit = "http://localhost:8082/accounts/"+toAcc+"/credit?amount="+amount;
        rest.postForObject(debit, null, String.class);
        rest.postForObject(credit, null, String.class);
        return "OK";
    }
}
