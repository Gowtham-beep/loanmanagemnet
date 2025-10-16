package org.example.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class EmiCalculator {
    // Formula: EMI = [P * r * (1+r)^n] / [(1+r)^n – 1]
    public static BigDecimal calculateMonthlyEmi(BigDecimal principal, double annualRate, int tenureMonths) {
        double monthlyRate = annualRate / (12 * 100); // convert annual to monthly interest rate
        double ratePow = Math.pow(1 + monthlyRate, tenureMonths);

        double emiDouble = (principal.doubleValue() * monthlyRate * ratePow) / (ratePow - 1);

        // Return as BigDecimal with 2 decimal precision
        return BigDecimal.valueOf(emiDouble).setScale(2, RoundingMode.HALF_UP);
    }
}
