package com.example.jobportal.controller;

import com.example.jobportal.dto.ApplicationResponse;
import com.example.jobportal.entity.Application;
import com.example.jobportal.service.ApplicationService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ApplicationResponse apply(
            Authentication authentication,
            @RequestParam Long jobId
    ) {
        String email = authentication.getName();

        return applicationService.apply(email, jobId);
    }
    @GetMapping
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }
}
