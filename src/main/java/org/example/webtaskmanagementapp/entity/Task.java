package org.example.webtaskmanagementapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.example.webtaskmanagementapp.enums.TaskStatus;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}