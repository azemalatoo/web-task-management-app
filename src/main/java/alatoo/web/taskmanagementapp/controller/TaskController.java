package alatoo.web.taskmanagementapp.controller;

import alatoo.web.taskmanagementapp.dto.TaskModel;
import alatoo.web.taskmanagementapp.response.ResponseApi;
import alatoo.web.taskmanagementapp.response.ResponseCode;
import alatoo.web.taskmanagementapp.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseApi<List<TaskModel>> getAllTasks() {
        List<TaskModel> tasks = taskService.getAllTasks();
        return new ResponseApi<>(tasks, ResponseCode.SUCCESS);
    }

    @GetMapping("/byUser")
    public ResponseApi<List<TaskModel>> getAllTasksByUserId(@RequestParam Long userId) {
        List<TaskModel> tasks = taskService.getTasksByUserId(userId);
        return new ResponseApi<>(tasks, ResponseCode.SUCCESS);
    }

    @GetMapping("/{id}")
    public ResponseApi<TaskModel> getTaskById(@PathVariable Long id) {
        TaskModel task = taskService.getTaskById(id);
        return new ResponseApi<>(task, ResponseCode.SUCCESS);
    }

    @PostMapping
    public ResponseApi<TaskModel> createTask(@RequestBody @Valid TaskModel taskModel) {
        TaskModel createdTask = taskService.createTask(taskModel);
        return new ResponseApi<>(createdTask, ResponseCode.SUCCESS);
    }

    @PutMapping("/{id}")
    public ResponseApi<TaskModel> updateTask(@PathVariable Long id, @RequestBody @Valid TaskModel taskModel) {
        TaskModel updatedTask = taskService.updateTask(id, taskModel);
        return new ResponseApi<>(updatedTask, ResponseCode.SUCCESS);
    }

    @DeleteMapping("/{id}")
    public ResponseApi<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseApi<>("Успешно удалено", ResponseCode.SUCCESS);
    }
}
