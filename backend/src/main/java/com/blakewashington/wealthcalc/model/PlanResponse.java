package com.blakewashington.wealthcalc.model;

import com.google.cloud.firestore.annotation.DocumentId;

import java.util.ArrayList;
import java.util.List;

public class PlanResponse {
    @DocumentId
    private String id;
    private int currentAge;
    private int retirementAge;
    private double monthlyContribution;
    private double interestRate;
    private List<YearlyProjection> projections;

    public PlanResponse() {}

    public PlanResponse(String id, int currentAge, int retirementAge,
                        double monthlyContribution, double interestRate,
                        List<YearlyProjection> projections) {
        this.id = id;
        this.currentAge = currentAge;
        this.retirementAge = retirementAge;
        this.monthlyContribution = monthlyContribution;
        this.interestRate = interestRate;
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

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getMonthlyContribution() {
        return monthlyContribution;
    }

    public void setMonthlyContribution(double monthlyContribution) {
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
}
