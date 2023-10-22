package com.example.e_salesperson;

import java.util.Date;

public class SalespersonInfo {
    private String name;
    private int month;
    private int year;
    private Date registrationDate;
    private double southernRegionCommission;
    private double coastalRegionCommission;
    private double northernRegionCommission;
    private double easternRegionCommission;
    private double lebanonCommission;
    private double monthlyCommission;

    public SalespersonInfo(String name, int month, int year, Date registrationDate, double southernRegionCommission, double coastalRegionCommission, double northernRegionCommission, double easternRegionCommission, double lebanonCommission, double monthlyCommission) {
        this.name = name;
        this.month = month;
        this.year = year;
        this.registrationDate = registrationDate;
        this.southernRegionCommission = southernRegionCommission;
        this.coastalRegionCommission = coastalRegionCommission;
        this.northernRegionCommission = northernRegionCommission;
        this.easternRegionCommission = easternRegionCommission;
        this.lebanonCommission = lebanonCommission;
        this.monthlyCommission = monthlyCommission;
    }



    // Getter methods for all the fields
}
