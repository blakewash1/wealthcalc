package com.blakewashington.wealthcalc.service;

import com.blakewashington.wealthcalc.exception.PlanCalculationException;
import com.blakewashington.wealthcalc.model.PlanRequest;
import com.blakewashington.wealthcalc.model.PlanResponse;
import com.blakewashington.wealthcalc.model.YearlyProjection;
import com.google.cloud.firestore.Firestore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PlanService {

    Logger logger = LoggerFactory.getLogger(PlanRequest.class);
    private final Firestore firestore;
    private static final int NUM_MONTHS = 12;
    private static final MathContext ROUND_UP = new MathContext(10, RoundingMode.HALF_UP);
    private static final BigDecimal ONE = BigDecimal.ONE;

    // default constructor for testing purposes only
    public PlanService() {
        this.firestore = null;
    }

    public PlanService(Firestore firestore) {
        this.firestore = firestore;
    }

    public PlanResponse calculatePlan(PlanRequest planRequest) throws Exception {
        PlanResponse planResponse = new PlanResponse();
        List<YearlyProjection> projectionsList = calculateYearlyProjections(planRequest);

        try {
            planResponse.setId(UUID.randomUUID().toString());
            planResponse.setCurrentAge(planRequest.getCurrentAge());
            planResponse.setRetirementAge(planRequest.getRetirementAge());
            planResponse.setMonthlyContribution(planRequest.getMonthlyContribution());
            planResponse.setInterestRate(planRequest.getInterestRate());
            planResponse.setStartingBalance(planRequest.getStartingBalance());
            planResponse.setProjections(projectionsList);

            if (!projectionsList.isEmpty()) {
                // get total of final YearlyProjection to get final balance
                YearlyProjection finalYearlyProjection = projectionsList.getLast();
                planResponse.setFinalBalance(finalYearlyProjection.getTotal());

            } else {
                planResponse.setFinalBalance(planResponse.getStartingBalance());
            }

        } catch (Exception ex) {
            logger.error("Unexpected error in calculatePlan: {}", ex.getMessage(), ex);
            throw new PlanCalculationException("An error occurred while calculating the plan.", ex);
        }

        return planResponse;
    }

    private List<YearlyProjection> calculateYearlyProjections(PlanRequest planRequest) throws Exception {
        List<YearlyProjection> projectionsList = new ArrayList<>();
        int yearsUntilRetirement = planRequest.getRetirementAge() - planRequest.getCurrentAge();

        BigDecimal total = planRequest.getStartingBalance();
        BigDecimal monthlyContribution = planRequest.getMonthlyContribution();
        BigDecimal annualRate = planRequest.getInterestRate();
        BigDecimal monthsPerYear = new BigDecimal(NUM_MONTHS);

        /*
         * performing compound interest calculation for each year, assuming the
         * interest is added on a *monthly* basis
         * NOTE: starting year at 1 to represent years going forward from current year
         */
        for (int year = 1; year <= yearsUntilRetirement; year++) {
            for (int month = 0; month < NUM_MONTHS; month++) {
                // add monthly contribution to the total
                total = total.add(monthlyContribution, ROUND_UP);

                // compound interest formula: total = total * (1 + r/12)
                // where r is annual interest rate (as a decimal)
                BigDecimal monthlyRate = annualRate.divide(monthsPerYear, ROUND_UP);
                total = total.multiply(ONE.add(monthlyRate, ROUND_UP), ROUND_UP);
            }

            // create new YearlyProjection with updated year and total
            YearlyProjection projection = new YearlyProjection();
            projection.setYear(year);
            projection.setAge(planRequest.getCurrentAge() + year);
            projection.setTotal(total.setScale(2, RoundingMode.HALF_UP)); // set to two decimal places
            projectionsList.add(projection);
        }

        return projectionsList;
    }
}
