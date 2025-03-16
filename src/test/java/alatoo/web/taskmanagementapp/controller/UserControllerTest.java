package alatoo.web.taskmanagementapp.controller;

import alatoo.web.taskmanagementapp.dto.UserModel;
import alatoo.web.taskmanagementapp.service.UserService;
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
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private UserModel userModel;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        userModel = new UserModel();
        userModel.setId(1L);
        userModel.setUsername("John Doe");
        userModel.setPassword("123");
    }

    @Test
    public void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(userModel));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("John Doe"))  // Use username
                .andExpect(jsonPath("$[0].password").value("123"));  // Use password
    }

    @Test
    public void testGetUserById() throws Exception {
        when(userService.getUser(1L)).thenReturn(userModel);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("John Doe"))  // Use username
                .andExpect(jsonPath("$.password").value("123"));  // Use password
    }

    @Test
    public void testCreateUser() throws Exception {
        UserModel newUser = new UserModel();
        newUser.setUsername("Jane Doe");
        newUser.setPassword("123");

        when(userService.createUser(newUser)).thenReturn(newUser);

        mockMvc.perform(post("/api/users")
                        .contentType("application/json")
                        .content("{ \"username\": \"Jane Doe\", \"password\": \"123\" }"))  // Use correct keys
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("Jane Doe"))
                .andExpect(jsonPath("$.password").value("123"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        UserModel updatedUser = new UserModel();
        updatedUser.setId(1L);
        updatedUser.setUsername("Johnathan Doe");
        updatedUser.setPassword("test123");

        when(userService.updateUser(1L, updatedUser)).thenReturn(updatedUser);

        mockMvc.perform(put("/api/users/1")
                        .contentType("application/json")
                        .content("{ \"username\": \"Johnathan Doe\", \"password\": \"test123\" }"))  // Use correct keys
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Johnathan Doe"))
                .andExpect(jsonPath("$.password").value("test123"));
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());
    }
}
