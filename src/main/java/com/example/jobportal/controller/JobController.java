package com.example.jobportal.controller;

import com.example.jobportal.dto.CreateJobRequest;
import com.example.jobportal.entity.Job;
import com.example.jobportal.service.JobService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.jobportal.dto.JobResponse;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;
    public JobController(JobService jobService){
        this.jobService=jobService;
    }

    @GetMapping
    public List<JobResponse> getAllJobs(){
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Long id){
        return jobService.getJobById(id);
    }

    @PostMapping
    public Job createJob(@Valid @RequestBody CreateJobRequest createJobRequest ) {
        return jobService.createJob(createJobRequest);
    }
    @DeleteMapping("/{id}")
    public String deleteJobById(@PathVariable Long id){
        jobService.deleteJobById(id);
        return "Deleted Successfully !";
    }

    @PutMapping("/{id}")
    public Job updateJob(@PathVariable Long id,@Valid @RequestBody CreateJobRequest createJobRequest ){
        return jobService.updateJob(id,createJobRequest);
    }
    @GetMapping("/search")
    public List<Job> searchJobs(@RequestParam String title) {
        return jobService.searchJobs(title);
    }

    @GetMapping("/page")
    public Page<Job> getJobs(Pageable pageable) {
        return jobService.getJobs(pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin-test")
    public String adminTest(){
        return "Admin access granted";
    }
}
