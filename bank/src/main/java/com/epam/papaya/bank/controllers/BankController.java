package com.epam.papaya.bank.controllers;

import com.epam.papaya.bank.services.BankService;
import com.epam.papaya.bank.services.domain.BankClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Evgeny Borisov
 */
@RestController
public class BankController {


    @Autowired
    private BankService bankService;

    @GetMapping("/loan")
    public String loan(@RequestParam String name, @RequestParam String family, @RequestParam int amount) {
        return bankService.loan(BankClient.builder().name(name).family(family).build(),amount);
    }

}
