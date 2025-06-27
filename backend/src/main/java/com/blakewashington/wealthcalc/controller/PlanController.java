package com.blakewashington.wealthcalc.controller;

import com.blakewashington.wealthcalc.model.PlanRequest;
import com.blakewashington.wealthcalc.model.PlanResponse;
import com.blakewashington.wealthcalc.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/planner")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<PlanResponse> calculatePlan(@RequestBody PlanRequest planRequest) throws Exception {
        try {
            PlanResponse planResponse = planService.calculateAndStorePlan(planRequest);
            return new ResponseEntity<>(planResponse, HttpStatus.OK);

        } catch (Exception e) {
            // Log the full stack trace (for server logs)
            e.printStackTrace();

            // Return the message for debugging
            String errorMessage = "/calculate failed: " + e.getMessage();
            return ResponseEntity.status(500).body((PlanResponse) Map.of("error", errorMessage));
        }
    }
}
