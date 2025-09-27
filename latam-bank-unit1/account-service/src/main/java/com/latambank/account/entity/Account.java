package com.latambank.account.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private String type; // SAVINGS, CURRENT
    private Double balance;
    private String currency; // e.g., USD, INR, BRL

    
}
