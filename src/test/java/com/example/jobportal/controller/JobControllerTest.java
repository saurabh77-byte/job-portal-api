package com.example.jobportal.controller;

import com.example.jobportal.dto.CreateJobRequest;
import com.example.jobportal.dto.JobResponse;
import com.example.jobportal.entity.Job;
import com.example.jobportal.service.CustomUserDetailsService;
import com.example.jobportal.service.JobService;
import com.example.jobportal.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import com.example.jobportal.dto.CreateJobRequest;
import com.example.jobportal.entity.Job;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobController.class)
class JobControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JobService jobService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void getAllJobs() throws Exception {

        List<JobResponse> jobs = List.of(
                new JobResponse(
                        1L,
                        "Java Developer",
                        "Pune",
                        80000.0,
                        1L,
                        "ABC Company"
                )
        );

        when(jobService.getAllJobs()).thenReturn(jobs);

        mockMvc.perform(get("/jobs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Java Developer"))
                .andExpect(jsonPath("$[0].location").value("Pune"))
                .andExpect(jsonPath("$[0].companyName").value("ABC Company"));
    }

    @Test
    void createJob() throws Exception {

        Job job = new Job();
        job.setTitle("Java Developer");
        job.setLocation("Pune");
        job.setSalary(80000.0);

        when(jobService.createJob(any(CreateJobRequest.class)))
                .thenReturn(job);

        mockMvc.perform(
                        post("/jobs")
                                .contentType(APPLICATION_JSON)
                                .content("""
                            {
                                "title": "Java Developer",
                                "companyId": 1,
                                "location": "Pune",
                                "salary": 80000
                            }
                            """)
                )
                .andExpect(status().isOk());
    }

}