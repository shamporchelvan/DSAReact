package com.latambank.loi.controller;

import com.latambank.loi.entity.LetterOfIntent;
import com.latambank.loi.repository.LetterOfIntentRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/loi")
public class LoiController {
    private final LetterOfIntentRepository repo;
    public LoiController(LetterOfIntentRepository repo){ this.repo = repo; }

    @PostMapping public LetterOfIntent create(@RequestBody LetterOfIntent l){
        if(l.getCreatedOn()==null) l.setCreatedOn(LocalDate.now());
        if(l.getStatus()==null) l.setStatus("DRAFT");
        return repo.save(l);
    }
    @GetMapping public List<LetterOfIntent> all(){ return repo.findAll(); }
    @GetMapping("/{id}") public LetterOfIntent get(@PathVariable Long id){ return repo.findById(id).orElseThrow(); }
    @PostMapping("/{id}/submit") public LetterOfIntent submit(@PathVariable Long id){
        LetterOfIntent l = repo.findById(id).orElseThrow();
        l.setStatus("SUBMITTED"); return repo.save(l);
    }
    @PostMapping("/{id}/approve") public LetterOfIntent approve(@PathVariable Long id){
        LetterOfIntent l = repo.findById(id).orElseThrow();
        l.setStatus("APPROVED"); return repo.save(l);
    }
    @PostMapping("/{id}/reject") public LetterOfIntent reject(@PathVariable Long id){
        LetterOfIntent l = repo.findById(id).orElseThrow();
        l.setStatus("REJECTED"); return repo.save(l);
    }
}
