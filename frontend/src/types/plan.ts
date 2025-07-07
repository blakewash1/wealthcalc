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
    total: string;
};

export type PlanResponse = {
    finalBalance: string;
    projections: YearlyProjection[];
};