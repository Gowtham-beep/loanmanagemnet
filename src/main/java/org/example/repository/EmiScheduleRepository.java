package org.example.repository;

import org.example.entity.EmiSchedule;
import org.example.entity.HomeLoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmiScheduleRepository extends JpaRepository<EmiSchedule, Long> {
    List<EmiSchedule> findByLoan(HomeLoanApplication loan);
}
