package com.latambank.account.controller;

import com.latambank.account.entity.Account;
import com.latambank.account.repository.AccountRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountRepository repo;
    public AccountController(AccountRepository repo){ this.repo = repo; }

    @PostMapping public Account open(@RequestBody Account a){
        if(a.getBalance()==null) a.setBalance(0.0);
        if(a.getCurrency()==null) a.setCurrency("USD");
        return repo.save(a);
    }
    @GetMapping public List<Account> all(){ return repo.findAll(); }
    @GetMapping("/{id}") public Account get(@PathVariable Long id){ return repo.findById(id).orElseThrow(); }
    @GetMapping("/byCustomer/{customerId}") public List<Account> byCustomer(@PathVariable Long customerId){ return repo.findByCustomerId(customerId); }

    @PostMapping("/{id}/debit")
    public Account debit(@PathVariable Long id, @RequestParam Double amount){
        Account acc = repo.findById(id).orElseThrow();
        acc.setBalance(acc.getBalance() - amount);
        return repo.save(acc);
    }
    @PostMapping("/{id}/credit")
    public Account credit(@PathVariable Long id, @RequestParam Double amount){
        Account acc = repo.findById(id).orElseThrow();
        acc.setBalance(acc.getBalance() + amount);
        return repo.save(acc);
    }
}
