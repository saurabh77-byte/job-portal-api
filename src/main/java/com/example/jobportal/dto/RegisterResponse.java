package com.example.jobportal.dto;

public class RegisterResponse {
    private Long id;
    private String email;
    private String name;

    public RegisterResponse(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
}
