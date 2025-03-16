package alatoo.web.taskmanagementapp.service;

import alatoo.web.taskmanagementapp.dto.TaskModel;

import java.util.List;

public interface TaskService {

    List<TaskModel> getAllTasks();

    TaskModel getTaskById(Long id);

    TaskModel createTask(TaskModel taskModel);

    TaskModel updateTask(Long id, TaskModel taskModel);

    void deleteTask(Long id);

    List<TaskModel> getTasksByUserId(Long userId);
}
