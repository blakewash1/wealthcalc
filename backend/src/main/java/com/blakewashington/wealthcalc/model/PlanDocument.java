package com.blakewashington.wealthcalc.model;

import java.time.Instant;

public class PlanDocument {
    private PlanRequest planRequest;
    private PlanResponse planResponse;
    private Instant timestamp;

    public PlanDocument() {}

    public PlanDocument(PlanRequest planRequest, PlanResponse planResponse, Instant timestamp) {
        this.planRequest = planRequest;
        this.planResponse = planResponse;
        this.timestamp = timestamp;
    }

    public PlanRequest getPlanRequest() {
        return planRequest;
    }

    public void setPlanRequest(PlanRequest planRequest) {
        this.planRequest = planRequest;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public PlanResponse getPlanResponse() {
        return planResponse;
    }

    public void setPlanResponse(PlanResponse planResponse) {
        this.planResponse = planResponse;
    }
}
