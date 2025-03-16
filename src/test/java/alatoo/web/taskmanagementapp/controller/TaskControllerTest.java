package alatoo.web.taskmanagementapp.controller;

import alatoo.web.taskmanagementapp.dto.TaskModel;
import alatoo.web.taskmanagementapp.response.ResponseCode;
import alatoo.web.taskmanagementapp.service.TaskService;
import alatoo.web.taskmanagementapp.enums.TaskStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllTasks() throws Exception {
        TaskModel task1 = new TaskModel(1L, "Task 1", "Description of Task 1", TaskStatus.PENDING, 100L);
        TaskModel task2 = new TaskModel(2L, "Task 2", "Description of Task 2", TaskStatus.COMPLETED, 101L);
        List<TaskModel> tasks = List.of(task1, task2);

        when(taskService.getAllTasks()).thenReturn(tasks);

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.length()").value(2))
                .andExpect(jsonPath("$.result[0].title").value("Task 1"))
                .andExpect(jsonPath("$.result[1].title").value("Task 2"))
                .andExpect(jsonPath("$.result[0].status").value(TaskStatus.PENDING.toString()))
                .andExpect(jsonPath("$.result[1].status").value(TaskStatus.COMPLETED.toString()))
                .andExpect(jsonPath("$.code").value(ResponseCode.SUCCESS.name()));
    }

    @Test
    void testGetTaskById() throws Exception {
        TaskModel task = new TaskModel(1L, "Task 1", "Description of Task 1", TaskStatus.PENDING, 100L);

        when(taskService.getTaskById(1L)).thenReturn(task);

        mockMvc.perform(get("/api/tasks/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.title").value("Task 1"))
                .andExpect(jsonPath("$.result.description").value("Description of Task 1"))
                .andExpect(jsonPath("$.result.status").value(TaskStatus.PENDING.toString()))
                .andExpect(jsonPath("$.result.userId").value(100L))
                .andExpect(jsonPath("$.code").value(ResponseCode.SUCCESS.name()));
    }

    @Test
    void testCreateTask() throws Exception {
        TaskModel task = new TaskModel(1L, "Task 1", "Description of Task 1", TaskStatus.PENDING, 100L);

        when(taskService.createTask(any(TaskModel.class))).thenReturn(task);

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.title").value("Task 1"))
                .andExpect(jsonPath("$.result.description").value("Description of Task 1"))
                .andExpect(jsonPath("$.result.status").value(TaskStatus.PENDING.toString()))
                .andExpect(jsonPath("$.result.userId").value(100L))
                .andExpect(jsonPath("$.code").value(ResponseCode.SUCCESS.name()));
    }

    @Test
    void testUpdateTask() throws Exception {
        TaskModel task = new TaskModel(1L, "Updated Task", "Updated description", TaskStatus.IN_PROGRESS, 101L);

        when(taskService.updateTask(eq(1L), any(TaskModel.class))).thenReturn(task);

        mockMvc.perform(put("/api/tasks/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.title").value("Updated Task"))
                .andExpect(jsonPath("$.result.description").value("Updated description"))
                .andExpect(jsonPath("$.result.status").value(TaskStatus.IN_PROGRESS.toString()))
                .andExpect(jsonPath("$.result.userId").value(101L))
                .andExpect(jsonPath("$.code").value(ResponseCode.SUCCESS.name()));
    }

    @Test
    void testDeleteTask() throws Exception {
        doNothing().when(taskService).deleteTask(1L);

        mockMvc.perform(delete("/api/tasks/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResponseCode.SUCCESS.name()));

        verify(taskService, times(1)).deleteTask(1L);
    }
}
