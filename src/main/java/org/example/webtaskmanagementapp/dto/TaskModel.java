package org.example.webtaskmanagementapp.dto;

import lombok.Builder;
import lombok.Data;
import org.example.webtaskmanagementapp.enums.TaskStatus;

@Builder
@Data
public class TaskModel {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Long userId;
}
