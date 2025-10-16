package org.example.repository;

import org.example.entity.HomeLoanApplication;
import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeLoanRepository extends JpaRepository<HomeLoanApplication, Long> {
    List<HomeLoanApplication> findByUser(User user);
}
