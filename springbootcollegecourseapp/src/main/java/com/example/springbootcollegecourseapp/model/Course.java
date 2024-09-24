package com.example.springbootcollegecourseapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private Double fee;
    private Integer duration; // in months
    private String accommodationType;
    private Double accommodationFee;

    @ManyToOne
    @JoinColumn(name = "college_id", nullable = false)
    @JsonIgnore
    private College college;

}


