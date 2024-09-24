package com.example.springbootcollegecourseapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootcollegecourseapp.exception.ResourceNotFoundException;
import com.example.springbootcollegecourseapp.model.College;
import com.example.springbootcollegecourseapp.model.Course;
import com.example.springbootcollegecourseapp.repository.CollegeRepository;
import com.example.springbootcollegecourseapp.repository.CourseRepository;

import java.util.List;



@Service
public class CollegeService {
    
    @Autowired
    private CollegeRepository collegeRepository;
    @Autowired
    private CourseRepository  courseRepository;

    // Get all colleges
    public List<College> getAllColleges() {
        return collegeRepository.findAll();
    }

    // Get a single college by ID
    public College getCollegeById(Long id) {
        return collegeRepository.findById(id).orElseThrow(() -> new RuntimeException("College not found"));
    }

    // Create a new college

   public College createCollege(College college) {
    
    if (college.getCourses() != null) {
        for (Course course : college.getCourses()) {
            course.setCollege(college); 
        }
    }
    return collegeRepository.save(college);
}
//update  a college


public College updateCollege(Long collegeId, College updatedCollege) {
    // Fetch the college by its ID
    College existingCollege = collegeRepository.findById(collegeId)
        .orElseThrow(() -> new ResourceNotFoundException("College not found with id: " + collegeId));

    // Update the college name if provided
    if (updatedCollege.getName() != null) {
        existingCollege.setName(updatedCollege.getName());
    }

    // Handle course updates if provided
    if (updatedCollege.getCourses() != null) {
        for (Course updatedCourse : updatedCollege.getCourses()) {
            // If the course ID is provided, update that specific course
            if (updatedCourse.getId() != null) {
                Course existingCourse = courseRepository.findById(updatedCourse.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + updatedCourse.getId()));

                // Update fields of the existing course if they are provided
                if (updatedCourse.getDuration() != null) {
                    existingCourse.setDuration(updatedCourse.getDuration());
                }
                if (updatedCourse.getFee() != null) {
                    existingCourse.setFee(updatedCourse.getFee());
                }
                if (updatedCourse.getAccommodationType() != null) {
                    existingCourse.setAccommodationType(updatedCourse.getAccommodationType());
                }
                if (updatedCourse.getAccommodationFee() != null) {
                    existingCourse.setAccommodationFee(updatedCourse.getAccommodationFee());
                }
                
                // Set the college for the course to ensure association is maintained
                existingCourse.setCollege(existingCollege);

            } else {
                // If no course ID is provided, treat this as a new course to be added
                updatedCourse.setCollege(existingCollege); // Associate the new course with the college
                existingCollege.getCourses().add(updatedCourse); // Add the new course to the college
            }
        }
    }

    // Save the updated college and courses
    return collegeRepository.save(existingCollege);
}




// Delete a college by ID
    public void deleteCollege(Long collegeId) {
        // Check if the college exists
        if (!collegeRepository.existsById(collegeId)) {
            throw new ResourceNotFoundException("College not found with id: " + collegeId);
        }
        // Delete the college, which will cascade and delete associated courses
        collegeRepository.deleteById(collegeId);
    }
}
