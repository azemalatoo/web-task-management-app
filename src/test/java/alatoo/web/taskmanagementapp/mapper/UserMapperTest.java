package alatoo.web.taskmanagementapp.mapper;

import alatoo.web.taskmanagementapp.dto.TaskModel;
import alatoo.web.taskmanagementapp.dto.UserModel;
import alatoo.web.taskmanagementapp.entity.Task;
import alatoo.web.taskmanagementapp.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserMapperTest {

    @InjectMocks
    private UserMapper userMapper;

    @Mock
    private TaskMapper taskMapper;

    @Mock
    private Task task1;

    @Mock
    private Task task2;

    @Mock
    private TaskModel taskModel1;

    @Mock
    private TaskModel taskModel2;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks

        user = new User();
        user.setId(1L);
        user.setUsername("TestUser");

        task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Task1");

        task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Task2");

        taskModel1 = new TaskModel();
        taskModel1.setId(1L);
        taskModel1.setTitle("Task1");

        taskModel2 = new TaskModel();
        taskModel2.setId(2L);
        taskModel2.setTitle("Task2");

        user.setTasks(Arrays.asList(task1, task2));
    }

    @Test
    public void toDTO_ValidUser_ReturnsUserModel() {
        when(taskMapper.toDTO(task1)).thenReturn(taskModel1);
        when(taskMapper.toDTO(task2)).thenReturn(taskModel2);

        UserModel userModel = userMapper.toDTO(user);

        assertNotNull(userModel, "UserModel should not be null");
        assertEquals(user.getId(), userModel.getId(), "User ID should match");
        assertEquals(user.getUsername(), userModel.getUsername(), "Username should match");

        List<TaskModel> tasks = userModel.getTasks();
        assertNotNull(tasks, "Tasks should not be null");
        assertEquals(2, tasks.size(), "User should have two tasks");

        assertEquals(taskModel1.getId(), tasks.get(0).getId(), "First task ID should match");
        assertEquals(taskModel2.getId(), tasks.get(1).getId(), "Second task ID should match");
    }

    @Test
    public void toDTO_UserWithoutTasks_ReturnsUserModelWithEmptyTasks() {
        user.setTasks(null);

        UserModel userModel = userMapper.toDTO(user);

        assertNotNull(userModel, "UserModel should not be null");
        assertEquals(user.getId(), userModel.getId(), "User ID should match");
        assertEquals(user.getUsername(), userModel.getUsername(), "Username should match");

        assertNull(userModel.getTasks(), "Tasks should be null for user with no tasks");
    }

    @Test
    public void toEntity_ValidUserModel_ReturnsUser() {
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setUsername("TestUser");

        User userEntity = userMapper.toEntity(userModel);

        assertNotNull(userEntity, "User entity should not be null");
        assertEquals(userModel.getId(), userEntity.getId(), "User ID should match");
        assertEquals(userModel.getUsername(), userEntity.getUsername(), "Username should match");
    }
}
