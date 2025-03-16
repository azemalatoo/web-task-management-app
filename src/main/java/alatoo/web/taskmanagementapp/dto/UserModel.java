package alatoo.web.taskmanagementapp.dto;

import java.util.List;

public class UserModel {

    private Long id;
    private String username;
    private String password;
    private List<TaskModel> tasks;

    public UserModel() {}

    public UserModel(Long id, String username, String password, List<TaskModel> tasks) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<TaskModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskModel> tasks) {
        this.tasks = tasks;
    }

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
