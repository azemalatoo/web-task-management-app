package alatoo.web.taskmanagementapp.controller;

import alatoo.web.taskmanagementapp.dto.TaskModel;
import alatoo.web.taskmanagementapp.enums.TaskStatus;
import alatoo.web.taskmanagementapp.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class TaskControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private TaskModel taskModel;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();

        taskModel = new TaskModel();
        taskModel.setId(1L);
        taskModel.setTitle("Test Task");
        taskModel.setDescription("Test Task Description");
        taskModel.setStatus(TaskStatus.PENDING);
    }

    // Test for fetching all tasks
    @Test
    public void testGetAllTasks() throws Exception {
        when(taskService.getAllTasks()).thenReturn(List.of(taskModel));

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())  // Check if the response is an array
                .andExpect(jsonPath("$").isNotEmpty());  // Check if the array is not empty
    }

    // Test for fetching a task by ID
    @Test
    public void testGetTaskById() throws Exception {
        when(taskService.getTaskById(1L)).thenReturn(taskModel);

        mockMvc.perform(get("/api/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());  // Check if the response body is not empty
    }

    // Test for creating a task
    @Test
    public void testCreateTask() throws Exception {
        TaskModel newTask = new TaskModel();
        newTask.setTitle("New Task");
        newTask.setDescription("This is a new task.");
        newTask.setStatus(TaskStatus.PENDING);

        when(taskService.createTask(newTask)).thenReturn(newTask);

        mockMvc.perform(post("/api/tasks")
                        .contentType("application/json")
                        .content("{ \"title\": \"New Task\", \"description\": \"This is a new task.\", \"status\": \"PENDING\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isNotEmpty());  // Ensure the response is not empty
    }

    // Test for updating a task
    @Test
    public void testUpdateTask() throws Exception {
        TaskModel updatedTask = new TaskModel();
        updatedTask.setId(1L);
        updatedTask.setTitle("Updated Task");
        updatedTask.setDescription("Updated Task Description");
        updatedTask.setStatus(TaskStatus.IN_PROGRESS);

        // Mock the service to return the updated task
        when(taskService.updateTask(1L, updatedTask)).thenReturn(updatedTask);

        mockMvc.perform(put("/api/tasks/1")
                        .contentType("application/json")
                        .content("{ \"title\": \"Updated Task\", \"description\": \"Updated Task Description\", \"status\": \"IN_PROGRESS\" }"))
                .andExpect(status().isOk())  // Ensure the status code is 200 OK
                .andExpect(jsonPath("$").isNotEmpty())  // Check if the response body is not empty
                .andExpect(jsonPath("$.id").value(1))  // You can also check if the ID is correct
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));  // Check the updated status
    }

    // Test for deleting a task
    @Test
    public void testDeleteTask() throws Exception {
        mockMvc.perform(delete("/api/tasks/1"))
                .andExpect(status().isNoContent()); // No content response after successful deletion
    }
}
