package org.example.webtaskmanagementapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.scheduling.config.Task;

import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String username;
    
    @NotBlank
    private String password;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Task> tasks;
}