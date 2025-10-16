package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeLoanApplication {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private BigDecimal amount;
    private Integer tenureMonths;
    private BigDecimal propertyValue;
    private String status = "PENDING";
    private Integer tenure;
    private Double interestRate = Double.valueOf(7.5);
    private Instant appliedAt = Instant.now();
}
