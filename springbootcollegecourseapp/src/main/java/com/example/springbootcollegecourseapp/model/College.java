package com.example.springbootcollegecourseapp.model;

import java.util.List;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

public class College {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Course> courses;



}
