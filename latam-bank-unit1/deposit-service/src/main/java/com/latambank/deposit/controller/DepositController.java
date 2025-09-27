package com.latambank.deposit.controller;

import com.latambank.deposit.entity.Deposit;
import com.latambank.deposit.repository.DepositRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/deposits")
public class DepositController {
    private final DepositRepository repo;
    public DepositController(DepositRepository repo){ this.repo = repo; }

    @PostMapping public Deposit create(@RequestBody Deposit d){ return repo.save(d); }
    @GetMapping public List<Deposit> all(){ return repo.findAll(); }
    @GetMapping("/{id}") public Deposit get(@PathVariable Long id){ return repo.findById(id).orElseThrow(); }

    @GetMapping("/{id}/maturity")
    public double maturity(@PathVariable Long id){
        Deposit d = repo.findById(id).orElseThrow();
        double r = d.getAnnualRate();
        int m = d.getTenureMonths();
        if("FIXED".equalsIgnoreCase(d.getDepositType())){
            double P = d.getPrincipal();
            return P * Math.pow(1 + r/12.0, m);
        } else {
            // Simple approximation for RD maturity: sum of monthly installments compounded monthly
            double I = d.getMonthlyInstallment();
            double total = 0.0;
            for(int k=0;k<m;k++){
                total += I * Math.pow(1 + r/12.0, m-k);
            }
            return total;
        }
    }
}
