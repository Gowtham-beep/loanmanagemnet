package org.example.controller;

import org.example.entity.HomeLoanApplication;
import org.example.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/loans")
@CrossOrigin
public class AdminController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public ResponseEntity<List<HomeLoanApplication>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<HomeLoanApplication> approve(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.approveLoan(id));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<HomeLoanApplication> reject(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.rejectLoan(id));
    }
}
