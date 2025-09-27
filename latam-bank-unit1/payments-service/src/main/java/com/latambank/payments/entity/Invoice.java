package com.latambank.payments.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Invoice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vendor;
    private Double amount;
    private String currency;
    private String status; // PENDING, PAID

    @ManyToOne
    @JoinColumn(name="batch_id")
    private InvoiceBatch batch;
}
