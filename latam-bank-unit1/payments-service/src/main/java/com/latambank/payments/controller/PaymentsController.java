package com.latambank.payments.controller;

import com.latambank.payments.entity.*;
import com.latambank.payments.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;
import java.util.*;

@RestController
@RequestMapping("/payments")
public class PaymentsController {

    private final InvoiceBatchRepository batchRepo;
    private final InvoiceRepository invoiceRepo;

    public PaymentsController(InvoiceBatchRepository b, InvoiceRepository i) {
        this.batchRepo = b;
        this.invoiceRepo = i;
    }

    @PostMapping("/batch")
    public InvoiceBatch createBatch(@RequestBody InvoiceBatch batch) {
        if (batch.getStatus() == null) batch.setStatus("CREATED");

        double sum = 0.0;
        for (Invoice inv : batch.getInvoices()) {
            inv.setBatch(batch);
            if (inv.getStatus() == null) inv.setStatus("PENDING");
            sum += inv.getAmount() != null ? inv.getAmount() : 0.0;
        }
        batch.setTotalAmount(sum);

        return batchRepo.save(batch);
    }

    @GetMapping("/batches")
    public List<InvoiceBatch> allBatches() {
        return batchRepo.findAll();
    }

    @Transactional
    @PostMapping("/batch/{id}/process")
    public InvoiceBatch process(@PathVariable Long id) {
        InvoiceBatch b = batchRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Batch not found"));

        b.setStatus("PROCESSED");
        for (Invoice inv : b.getInvoices()) {
            inv.setStatus("PAID");
        }

        return batchRepo.save(b);
    }


        @GetMapping("/invoices")
    public List<Invoice> allInvoices() {
        return invoiceRepo.findAll();
    }
}
