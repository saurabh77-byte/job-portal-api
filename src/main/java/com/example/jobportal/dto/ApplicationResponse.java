package com.example.jobportal.dto;

public class ApplicationResponse {

    private Long id;
    private Long userId;
    private Long jobId;
    private String jobTitle;
    private String status;

    public ApplicationResponse(
            Long id,
            Long userId,
            Long jobId,
            String jobTitle,
            String status
    ) {
        this.id = id;
        this.userId = userId;
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getJobId() {
        return jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getStatus() {
        return status;
    }
}