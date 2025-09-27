package com.latambank.payments.repository;

import com.latambank.payments.entity.InvoiceBatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceBatchRepository extends JpaRepository<InvoiceBatch, Long> {}
