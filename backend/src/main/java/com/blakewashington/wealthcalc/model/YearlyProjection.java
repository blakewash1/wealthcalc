package com.blakewashington.wealthcalc.model;

public class YearlyProjection {
    private int year;
    private int age;
    private double total;

    public YearlyProjection(int year, int age) {}

    public YearlyProjection(int year, int age, double total) {
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
