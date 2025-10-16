package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmiSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private HomeLoanApplication loan;

    private LocalDate dueDate;
    private Double amount;
    private String paymentStatus; // PENDING, PAID
}
