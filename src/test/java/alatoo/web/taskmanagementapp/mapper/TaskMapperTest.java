package alatoo.web.taskmanagementapp.mapper;

import alatoo.web.taskmanagementapp.dto.TaskModel;
import alatoo.web.taskmanagementapp.entity.Task;
import alatoo.web.taskmanagementapp.entity.User;
import alatoo.web.taskmanagementapp.enums.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaskMapperTest {

    private TaskMapper taskMapper;
    private User user;
    private Task task;
    private TaskModel taskModel;

    @BeforeEach
    public void setUp() {
        taskMapper = new TaskMapper();

        user = Mockito.mock(User.class);
        Mockito.when(user.getId()).thenReturn(1L);  // Return a mock user ID

        task = new Task(1L, "Test Task", "This is a test task", TaskStatus.PENDING, user);

        taskModel = new TaskModel();
        taskModel.setId(1L);
        taskModel.setTitle("Test Task");
        taskModel.setDescription("This is a test task");
        taskModel.setStatus(TaskStatus.PENDING);
    }

    @Test
    void toDTOTest() {
        TaskModel taskModelConverted = taskMapper.toDTO(task);

        assertNotNull(taskModelConverted, "TaskModel should not be null");
        assertEquals(task.getId(), taskModelConverted.getId(), "Task ID should match");
        assertEquals(task.getTitle(), taskModelConverted.getTitle(), "Task title should match");
        assertEquals(task.getDescription(), taskModelConverted.getDescription(), "Task description should match");
        assertEquals(task.getStatus(), taskModelConverted.getStatus(), "Task status should match");
        assertEquals(task.getUser().getId(), taskModelConverted.getUserId(), "Task user ID should match");
    }

    @Test
    void toEntityTest() {
        Task taskConverted = taskMapper.toEntity(taskModel);

        assertEquals(taskModel.getId(), taskConverted.getId(), "Task ID should match");
        assertEquals(taskModel.getTitle(), taskConverted.getTitle(), "Task title should match");
        assertEquals(taskModel.getDescription(), taskConverted.getDescription(), "Task description should match");
        assertEquals(taskModel.getStatus(), taskConverted.getStatus(), "Task status should match");
    }
}
