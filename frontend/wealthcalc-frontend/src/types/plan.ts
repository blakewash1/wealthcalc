// src/types/plan.ts

export type PlanRequest = {
    currentAge: number;
    retirementAge: number;
    monthlyContribution: number;
    interestRate: number;
    startingBalance: number;
};

export type YearlyProjection = {
    age: number;
    total: number;
};

export type PlanResponse = {
    finalBalance: number;
    projections: YearlyProjection[];
};