package com.example.JobApplication_Final.repo;

import com.example.JobApplication_Final.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCompanyId(long companyId);
}
