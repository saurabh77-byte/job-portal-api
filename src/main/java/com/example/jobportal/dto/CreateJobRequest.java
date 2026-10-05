package com.example.jobportal.dto;

import jakarta.validation.constraints.*;

public class CreateJobRequest {

    @NotBlank
    private String title;

    @NotNull
    private Long companyId;

    @NotBlank
    private String location;

    @NotNull
    @Positive
    private Double salary;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long company) {
        this.companyId = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}