package com.example.springbootcollegecourseapp.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springbootcollegecourseapp.model.College;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {
    @Query("SELECT c FROM College c LEFT JOIN FETCH c.courses WHERE c.id = :collegeId")
    Optional<College> findByIdWithCourses(Long collegeId);
}
