package org.example.service;

import org.example.entity.*;
import org.example.repository.*;
import org.example.util.EmiCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class LoanService {

    @Autowired private HomeLoanRepository loanRepo;
    @Autowired private EmiScheduleRepository emiRepo;
    @Autowired private UserRepository userRepo;

    public HomeLoanApplication applyLoan(String email, BigDecimal amount, Integer tenure, BigDecimal propertyValue) {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        HomeLoanApplication loan = new HomeLoanApplication();
        loan.setUser(user);
        loan.setAmount(amount);
        loan.setTenure(tenure);
        loan.setPropertyValue(propertyValue);
        loan.setInterestRate(8.5); // fixed for demo
        loan.setStatus("PENDING");

        return loanRepo.save(loan);
    }

    public List<HomeLoanApplication> getLoansForUser(String email) {
        User user = userRepo.findByEmail(email).orElseThrow();
        return loanRepo.findByUser(user);
    }

    public List<HomeLoanApplication> getAllLoans() {
        return loanRepo.findAll();
    }

    public HomeLoanApplication approveLoan(Long id) {
        HomeLoanApplication loan = loanRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setStatus("APPROVED");
        loanRepo.save(loan);

        generateEmiSchedule(loan);
        return loan;
    }

    public HomeLoanApplication rejectLoan(Long id) {
        HomeLoanApplication loan = loanRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setStatus("REJECTED");
        return loanRepo.save(loan);
    }

    private void generateEmiSchedule(HomeLoanApplication loan) {
        BigDecimal emiAmount = EmiCalculator.calculateMonthlyEmi(
                loan.getAmount(),
                loan.getInterestRate(),
                loan.getTenure()
        );

        List<EmiSchedule> emis = new ArrayList<>();
        for (int i = 1; i <= loan.getTenure(); i++) {
            EmiSchedule emi = new EmiSchedule();
            emi.setLoan(loan);
            emi.setAmount(emiAmount.doubleValue()); // ✅ convert BigDecimal → Double
            emi.setPaymentStatus("PENDING");
            emi.setDueDate(LocalDate.now().plusMonths(i));
            emis.add(emi);
        }
        emiRepo.saveAll(emis);
    }



    public List<EmiSchedule> getEmiSchedule(Long loanId) {
        HomeLoanApplication loan = loanRepo.findById(loanId).orElseThrow();
        return emiRepo.findByLoan(loan);
    }

    public EmiSchedule markEmiPaid(Long emiId) {
        EmiSchedule emi = emiRepo.findById(emiId).orElseThrow();
        emi.setPaymentStatus("PAID");
        return emiRepo.save(emi);
    }
}
