package org.example.controller;

import org.example.entity.*;
import org.example.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/apply")
    public ResponseEntity<?> applyLoan(@RequestBody HomeLoanApplication req, Authentication auth) {
        String email = auth.getName();
        HomeLoanApplication loan = loanService.applyLoan(email, req.getAmount(), req.getTenure(), req.getPropertyValue());
        return ResponseEntity.ok(loan);
    }

    @GetMapping
    public ResponseEntity<List<HomeLoanApplication>> getLoans(Authentication auth) {
        return ResponseEntity.ok(loanService.getLoansForUser(auth.getName()));
    }

    @GetMapping("/{loanId}/emis")
    public ResponseEntity<List<EmiSchedule>> getEmis(@PathVariable Long loanId) {
        return ResponseEntity.ok(loanService.getEmiSchedule(loanId));
    }

    @PostMapping("/emi/{emiId}/pay")
    public ResponseEntity<?> payEmi(@PathVariable Long emiId) {
        return ResponseEntity.ok(loanService.markEmiPaid(emiId));
    }
}
