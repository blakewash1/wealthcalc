package com.blakewashington.wealthcalc.model;

import java.math.BigDecimal;

public class YearlyProjection {
    private int year;
    private int age;
    private BigDecimal total;

    public YearlyProjection(int year, int age) {}

    public YearlyProjection(int year, int age, BigDecimal total) {
        this.year = year;
        this.age = age;
        this.total = total;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
