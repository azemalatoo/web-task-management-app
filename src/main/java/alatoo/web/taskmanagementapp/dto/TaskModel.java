package alatoo.web.taskmanagementapp.dto;

import alatoo.web.taskmanagementapp.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskModel {

    private Long id;
    @NotBlank(message = "title cannot be blank")
    private String title;
    private String description;
    private TaskStatus status;
    @NotNull(message = "userId cannot be null")
    private Long userId;

    public TaskModel() {}

    public TaskModel(Long id, String title, String description, TaskStatus status, Long userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "TaskModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
