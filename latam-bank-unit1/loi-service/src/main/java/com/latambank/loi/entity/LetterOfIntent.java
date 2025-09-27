package com.latambank.loi.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class LetterOfIntent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private String beneficiary;
    private Double amount;
    private String purpose;
    private String status; // DRAFT, SUBMITTED, APPROVED, REJECTED
    private LocalDate createdOn;
}
