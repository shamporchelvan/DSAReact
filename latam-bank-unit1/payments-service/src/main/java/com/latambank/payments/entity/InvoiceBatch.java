package com.latambank.payments.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private String status; // CREATED, PROCESSED

    private Double totalAmount;

    @OneToMany(mappedBy="batch", cascade=CascadeType.ALL, orphanRemoval=true)
    @Builder.Default
    private List<Invoice> invoices = new ArrayList<>();
}

