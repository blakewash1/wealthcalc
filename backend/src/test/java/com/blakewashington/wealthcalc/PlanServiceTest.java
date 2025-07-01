package com.blakewashington.wealthcalc;

import com.blakewashington.wealthcalc.model.PlanRequest;
import com.blakewashington.wealthcalc.model.PlanResponse;
import com.blakewashington.wealthcalc.service.PlanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlanServiceTest {

    private PlanService planService;

    @BeforeEach
    public void setup() {
        planService = new PlanService();
    }

    //TODO: Test for negative values in all fields

    @Test
    void calculatePlanFindCorrectBalance() throws Exception {
        PlanRequest request = new PlanRequest(
               30,
               65,
               new BigDecimal("500"),
               new BigDecimal("0.07"),
               new BigDecimal("5000")
        );

        PlanResponse response = planService.calculatePlan(request);
        assertNotNull(response);
        assertNotNull(response.getFinalBalance());
    }

    @Test
    void calculatePlanZeroContributions() throws Exception {
        PlanRequest request = new PlanRequest(
                30,
                65,
                BigDecimal.ZERO,
                new BigDecimal("0.07"),
                new BigDecimal("5000")
        );

        PlanResponse response = planService.calculatePlan(request);
        assertNotNull(response);
        assertNotNull(response.getFinalBalance());
    }

    @Test
    void calculatePlanAlreadyRetired() throws Exception {
        PlanRequest request = new PlanRequest(
                65,
                65,
                new BigDecimal("500"),
                new BigDecimal("0.07"),
                new BigDecimal("5000")
        );

        PlanResponse response = planService.calculatePlan(request);
        assertNotNull(response);
        assertNotNull(response.getFinalBalance());

        BigDecimal expected = request.getStartingBalance();
        assertEquals(0, response.getFinalBalance().compareTo(expected));
    }

    @Test
    void calculatePlanNoInterest() throws Exception {
        PlanRequest request = new PlanRequest(
                30,
                65,
                new BigDecimal("500"),
                BigDecimal.ZERO,
                new BigDecimal("5000")
        );

        PlanResponse response = planService.calculatePlan(request);
        assertNotNull(response);
        assertNotNull(response.getFinalBalance());

        int years = request.getRetirementAge() - request.getCurrentAge();
        BigDecimal linearIncrease = request.getMonthlyContribution()
                .multiply(BigDecimal.valueOf(12))
                .multiply(BigDecimal.valueOf(years));
        BigDecimal expected = request.getStartingBalance().add(linearIncrease);

        assertEquals(0, response.getFinalBalance().compareTo(expected));
    }


}
