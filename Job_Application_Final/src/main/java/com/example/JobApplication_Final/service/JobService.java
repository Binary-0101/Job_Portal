package com.example.JobApplication_Final.service;

import com.example.JobApplication_Final.model.Job;
import com.example.JobApplication_Final.repo.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public void addJob(Job job) {
        jobRepository.save(job);
    }

    public Job findById(long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public boolean deleteJobById(long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateJob(long id, Job updatedJob) {
        Optional<Job> job = jobRepository.findById(id);

        if(job.isPresent()) {
            Job oldJob = job.get();
            oldJob.setTitle(updatedJob.getTitle());
            oldJob.setDescription(updatedJob.getDescription());
            oldJob.setLocation(updatedJob.getLocation());
            oldJob.setSalary(updatedJob.getSalary());

            jobRepository.save(oldJob);
            return true;
        }
        return false;
    }
}
