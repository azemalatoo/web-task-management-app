package alatoo.web.taskmanagementapp.controller;

import alatoo.web.taskmanagementapp.dto.UserModel;
import alatoo.web.taskmanagementapp.response.ResponseApi;
import alatoo.web.taskmanagementapp.response.ResponseCode;
import alatoo.web.taskmanagementapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseApi<List<UserModel>> getAllUsers() {
        List<UserModel> users = userService.getAllUsers();
        return new ResponseApi<>(users, ResponseCode.SUCCESS);
    }

    @GetMapping("/{id}")
    public ResponseApi<UserModel> getUserById(@PathVariable Long id) {
        UserModel userModel = userService.getUser(id);
        return new ResponseApi<>(userModel, ResponseCode.SUCCESS);
    }

    @PostMapping
    public ResponseApi<UserModel> createUser(@RequestBody @Valid UserModel userModel) {
        UserModel createdUser = userService.createUser(userModel);
        return new ResponseApi<>(createdUser, ResponseCode.SUCCESS);
    }

    @PutMapping("/{id}")
    public ResponseApi<UserModel> updateUser(@PathVariable Long id, @RequestBody @Valid UserModel userModel) {
        UserModel updatedUser = userService.updateUser(id, userModel);
        return new ResponseApi<>(updatedUser, ResponseCode.SUCCESS);
    }

    @DeleteMapping("/{id}")
    public ResponseApi<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseApi<>(null, ResponseCode.SUCCESS);
    }
}
