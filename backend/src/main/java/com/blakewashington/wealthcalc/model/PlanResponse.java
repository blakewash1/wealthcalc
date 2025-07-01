package com.blakewashington.wealthcalc.model;

import com.google.cloud.firestore.annotation.DocumentId;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PlanResponse {
    @DocumentId
    private String id;
    private int currentAge;
    private int retirementAge;
    private BigDecimal monthlyContribution;
    private BigDecimal interestRate;
    private BigDecimal startingBalance;
    private BigDecimal finalBalance;
    private List<YearlyProjection> projections;

    public PlanResponse() {}

    public PlanResponse(String id, int currentAge, int retirementAge, BigDecimal monthlyContribution,
                        BigDecimal interestRate, List<YearlyProjection> projections,  BigDecimal startingBalance) {
        this.id = id;
        this.currentAge = currentAge;
        this.retirementAge = retirementAge;
        this.monthlyContribution = monthlyContribution;
        this.interestRate = interestRate;
        this.startingBalance = startingBalance;
        this.projections = projections;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<YearlyProjection> getProjections() {
        return projections;
    }

    public void setProjections(List<YearlyProjection> projections) {
        this.projections = projections;
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

    public int getCurrentAge() {
        return currentAge;
    }

    public void setCurrentAge(int currentAge) {
        this.currentAge = currentAge;
    }

    public BigDecimal getStartingBalance() {
        return startingBalance;
    }

    public void setStartingBalance(BigDecimal startingBalance) {
        this.startingBalance = startingBalance;
    }

    public BigDecimal getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(BigDecimal finalBalance) {
        this.finalBalance = finalBalance;
    }
}
