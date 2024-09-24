package com.example.springbootcollegecourseapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springbootcollegecourseapp.model.College;
import com.example.springbootcollegecourseapp.service.CollegeService;

import java.util.List;



@RestController
@CrossOrigin(origins = "http://localhost:3000") 

@RequestMapping("/api/colleges")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    // Get all colleges
    @GetMapping
    public List<College> getAllColleges() {
        return collegeService.getAllColleges();
    }

    // Get a single college by ID
    @GetMapping("/{id}")
    public College getCollegeById(@PathVariable Long id) {
        return collegeService.getCollegeById(id);
    }

    // Create a new college
    @PostMapping
    public College createCollege(@RequestBody College college) {
        return collegeService.createCollege(college);
    }

    // Update a college by ID
    @PutMapping("/{id}")
    public ResponseEntity<College> updateCollege(@PathVariable Long id, @RequestBody College college) {
        College updatedCollege = collegeService.updateCollege(id, college);
        return ResponseEntity.ok(updatedCollege);
    }

    // Delete a college by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCollege(@PathVariable Long id) {
        collegeService.deleteCollege(id);
        return ResponseEntity.ok("delete successfully"); 
    }
}

