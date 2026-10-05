package com.example.jobportal.service;

import com.example.jobportal.dto.CreateJobRequest;
import com.example.jobportal.dto.JobResponse;
import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.Job;
import com.example.jobportal.exception.JobNotFoundException;
import com.example.jobportal.repository.CompanyRepository;
import com.example.jobportal.repository.JobRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public JobService(
            JobRepository jobRepository,
            CompanyRepository companyRepository
    ) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }
    public Job createJob(CreateJobRequest request) {

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() ->
                        new RuntimeException("Company not found")
                );

        Job job = new Job();

        job.setTitle(request.getTitle());
        job.setLocation(request.getLocation());
        job.setSalary(request.getSalary());
        job.setCompany(company);

        return jobRepository.save(job);
    }

    public List<JobResponse> getAllJobs() {
        return jobRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public Job getJobById(Long id){
        return jobRepository.findById(id).orElseThrow(()-> new JobNotFoundException("Job not found with id: " + id));
    }

    public void deleteJobById(Long id){
        jobRepository.deleteById(id);
    }
    public Job updateJob(Long id, CreateJobRequest request){
        Job job = jobRepository.findById(id).orElseThrow(()->new JobNotFoundException("Job not found with id : "+id));
        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() ->
                        new RuntimeException("Company not found")
                );
        job.setTitle(request.getTitle());
        job.setSalary(request.getSalary());
        job.setLocation(request.getLocation());
        job.setCompany(company);
        return jobRepository.save(job);
    }
    public List<Job> searchJobs(String title) {
        return jobRepository.findByTitleContainingIgnoreCase(title);
    }
    public Page<Job> getJobs(Pageable pageable) {
        return jobRepository.findAll(pageable);
    }

    private JobResponse toResponse(Job job) {

        Long companyId = null;
        String companyName = null;

        if (job.getCompany() != null) {
            companyId = job.getCompany().getId();
            companyName = job.getCompany().getName();
        }

        return new JobResponse(
                job.getId(),
                job.getTitle(),
                job.getLocation(),
                job.getSalary(),
                companyId,
                companyName
        );
    }

}
