package com.blakewashington.wealthcalc.controller;

import com.blakewashington.wealthcalc.model.PlanDocument;
import com.blakewashington.wealthcalc.model.PlanRequest;
import com.blakewashington.wealthcalc.model.PlanResponse;
import com.blakewashington.wealthcalc.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plan")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<PlanResponse> calculate(@RequestBody PlanRequest planRequest) throws Exception {
        PlanResponse planResponse = planService.calculatePlan(planRequest);
        return ResponseEntity.ok(planResponse);
    }

    @GetMapping("/plans/all")
    public ResponseEntity<List<PlanDocument>> getAllPlans() {
        return ResponseEntity.ok(planService.getAllPlans());
    }


}
