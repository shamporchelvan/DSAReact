package com.latambank.customer.controller;

import com.latambank.customer.entity.Customer;
import com.latambank.customer.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerRepository repo;
    public CustomerController(CustomerRepository repo){ this.repo = repo; }

    @PostMapping public Customer create(@RequestBody Customer c){ return repo.save(c); }
    @GetMapping public List<Customer> all(){ return repo.findAll(); }
    @GetMapping("/{id}") public Customer get(@PathVariable Long id){ return repo.findById(id).orElseThrow(); }
    @PutMapping("/{id}") public Customer update(@PathVariable Long id, @RequestBody Customer c){
        Customer x = repo.findById(id).orElseThrow();
        x.setName(c.getName()); x.setEmail(c.getEmail()); x.setPhone(c.getPhone());
        return repo.save(x);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ repo.deleteById(id); }
}
