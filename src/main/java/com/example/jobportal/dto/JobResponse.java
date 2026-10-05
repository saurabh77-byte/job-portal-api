package com.example.jobportal.dto;

public class JobResponse {

    private Long id;
    private String title;
    private String location;
    private Double salary;
    private Long companyId;
    private String companyName;

    public JobResponse(
            Long id,
            String title,
            String location,
            Double salary,
            Long companyId,
            String companyName
    ) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.salary = salary;
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public Double getSalary() {
        return salary;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }
}