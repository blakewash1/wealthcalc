package com.blakewashington.wealthcalc.exception;

public class PlanCalculationException extends RuntimeException {
    public PlanCalculationException(String message) {
        super(message);
    }

    public PlanCalculationException(String message, Throwable cause) {
        super(message, cause);
    }
}
