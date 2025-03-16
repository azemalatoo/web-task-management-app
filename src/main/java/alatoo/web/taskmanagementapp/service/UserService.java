package alatoo.web.taskmanagementapp.service;

import alatoo.web.taskmanagementapp.dto.UserModel;

import java.util.List;

public interface UserService {

    List<UserModel> getAllUsers(); // Read all users

    UserModel getUser(Long id); // Read a user by ID

    UserModel createUser(UserModel userModel); // Create a new user

    UserModel updateUser(Long id, UserModel userModel); // Update an existing user

    void deleteUser(Long id); // Delete a user by ID
}
