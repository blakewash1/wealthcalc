package com.blakewashington.wealthcalc.model;

public class PlanRequest {
    private int currentAge;
    private int retirementAge;
    private double monthlyContribution;
    private double interestRate;

    public PlanRequest() {}

    public PlanRequest(int currentAge, int retirementAge,
                       double monthlyContribution, double interestRate) {
        this.currentAge = currentAge;
        this.retirementAge = retirementAge;
        this.monthlyContribution = monthlyContribution;
        this.interestRate = interestRate;
    }

    public int getCurrentAge() {
        return currentAge;
    }

    public void setCurrentAge(int currentAge) {
        this.currentAge = currentAge;
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
}
