package com.blakewashington.wealthcalc.model;

import java.math.BigDecimal;

public class PlanRequest {
    private int currentAge;
    private int retirementAge;
    private BigDecimal monthlyContribution;
    private BigDecimal interestRate;
    private BigDecimal startingBalance;

    public PlanRequest() {}

    public PlanRequest(int currentAge, int retirementAge, BigDecimal monthlyContribution,
                       BigDecimal interestRate, BigDecimal startingBalance) {
        this.currentAge = currentAge;
        this.retirementAge = retirementAge;
        this.monthlyContribution = monthlyContribution;
        this.interestRate = interestRate;
        this.startingBalance = startingBalance;
    }

    public int getCurrentAge() {
        return currentAge;
    }

    public void setCurrentAge(int currentAge) {
        this.currentAge = currentAge;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getMonthlyContribution() {
        return monthlyContribution;
    }

    public void setMonthlyContribution(BigDecimal monthlyContribution) {
        this.monthlyContribution = monthlyContribution;
    }

    public int getRetirementAge() {
        return retirementAge;
    }

    public void setRetirementAge(int retirementAge) {
        this.retirementAge = retirementAge;
    }

    public BigDecimal getStartingBalance() {
        return startingBalance;
    }

    public void setStartingBalance(BigDecimal startingBalance) {
        this.startingBalance = startingBalance;
    }
}
