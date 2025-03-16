package alatoo.web.taskmanagementapp.controller;

import alatoo.web.taskmanagementapp.dto.TaskModel;
import alatoo.web.taskmanagementapp.response.ResponseApi;
import alatoo.web.taskmanagementapp.response.ResponseCode;
import alatoo.web.taskmanagementapp.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Get all tasks", description = "Retrieve a list of all tasks")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all tasks", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskModel.class)))
    @GetMapping
    public ResponseApi<List<TaskModel>> getAllTasks() {
        List<TaskModel> tasks = taskService.getAllTasks();
        return new ResponseApi<>(tasks, ResponseCode.SUCCESS);
    }

    @Operation(summary = "Get tasks by user ID", description = "Retrieve tasks assigned to a specific user by their user ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved tasks by user ID", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskModel.class)))
    @GetMapping("/byUser")
    public ResponseApi<List<TaskModel>> getAllTasksByUserId(@Parameter(description = "The ID of the user to filter tasks by") @RequestParam Long userId) {
        List<TaskModel> tasks = taskService.getTasksByUserId(userId);
        return new ResponseApi<>(tasks, ResponseCode.SUCCESS);
    }

    @Operation(summary = "Get task by ID", description = "Retrieve a specific task by its ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved task", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskModel.class)))
    @ApiResponse(responseCode = "404", description = "Task not found")
    @GetMapping("/{id}")
    public ResponseApi<TaskModel> getTaskById(@Parameter(description = "The ID of the task to retrieve") @PathVariable Long id) {
        TaskModel task = taskService.getTaskById(id);
        return new ResponseApi<>(task, ResponseCode.SUCCESS);
    }

    @Operation(summary = "Create a new task", description = "Create a new task with the provided task model")
    @ApiResponse(responseCode = "201", description = "Successfully created task", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskModel.class)))
    @PostMapping
    public ResponseApi<TaskModel> createTask(@RequestBody @Valid TaskModel taskModel) {
        TaskModel createdTask = taskService.createTask(taskModel);
        return new ResponseApi<>(createdTask, ResponseCode.SUCCESS);
    }

    @Operation(summary = "Update a task", description = "Update an existing task with the provided task model")
    @ApiResponse(responseCode = "200", description = "Successfully updated task", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskModel.class)))
    @ApiResponse(responseCode = "404", description = "Task not found")
    @PutMapping("/{id}")
    public ResponseApi<TaskModel> updateTask(@PathVariable Long id, @RequestBody @Valid TaskModel taskModel) {
        TaskModel updatedTask = taskService.updateTask(id, taskModel);
        return new ResponseApi<>(updatedTask, ResponseCode.SUCCESS);
    }

    @Operation(summary = "Delete a task", description = "Delete a task by its ID")
    @ApiResponse(responseCode = "200", description = "Successfully deleted task", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "404", description = "Task not found")
    @DeleteMapping("/{id}")
    public ResponseApi<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseApi<>("Successfully deleted", ResponseCode.SUCCESS);
    }
}
