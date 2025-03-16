package alatoo.web.taskmanagementapp.service;

import alatoo.web.taskmanagementapp.dto.TaskModel;

import java.util.List;

public interface TaskService {

    List<TaskModel> getAllTasks(); // Read all tasks

    TaskModel getTaskById(Long id); // Read a task by ID

    TaskModel createTask(TaskModel taskModel); // Create a new task

    TaskModel updateTask(Long id, TaskModel taskModel); // Update an existing task

    void deleteTask(Long id); // Delete a task by ID
}
