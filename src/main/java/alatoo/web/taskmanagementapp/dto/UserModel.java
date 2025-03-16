package alatoo.web.taskmanagementapp.dto;

import java.util.List;

public class UserModel {

    private Long id;
    private String username;
    private String password;
    private List<TaskModel> tasks;

    // Default constructor
    public UserModel() {}

    // All-args constructor
    public UserModel(Long id, String username, String password, List<TaskModel> tasks) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.tasks = tasks;
    }

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and Setter for tasks
    public List<TaskModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskModel> tasks) {
        this.tasks = tasks;
    }

    // Optional: toString method for easier debugging/logging
    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", tasks=" + tasks +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
