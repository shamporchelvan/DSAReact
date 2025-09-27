package com.latambank.forex.controller;

import com.latambank.forex.entity.FxRate;
import com.latambank.forex.repository.FxRateRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forex")
public class ForexController {
    private final FxRateRepository repo;
    public ForexController(FxRateRepository repo){ this.repo = repo; }

    @PostMapping("/rate") public FxRate upsert(@RequestBody FxRate r){ return repo.save(r); }
    @GetMapping("/rates") public List<FxRate> all(){ return repo.findAll(); }

    @GetMapping("/convert")
    public double convert(@RequestParam String from, @RequestParam String to, @RequestParam double amount){
        FxRate r = repo.findByFromCurrencyAndToCurrency(from, to).orElseThrow();
        return amount * r.getRate();
    }
}
