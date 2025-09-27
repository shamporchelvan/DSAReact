package com.latambank.deposit.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Deposit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private Long accountId;
    private String depositType; // FIXED or RECURRING
    private Double principal;   // for FIXED
    private Double monthlyInstallment; // for RECURRING
    private Double annualRate;  // e.g., 0.08 for 8%
    private Integer tenureMonths;
    private LocalDate startDate;
}
