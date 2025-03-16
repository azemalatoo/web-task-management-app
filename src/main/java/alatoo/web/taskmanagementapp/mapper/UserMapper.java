package alatoo.web.taskmanagementapp.mapper;

import alatoo.web.taskmanagementapp.dto.UserModel;
import alatoo.web.taskmanagementapp.entity.Task;
import alatoo.web.taskmanagementapp.dto.TaskModel;
import alatoo.web.taskmanagementapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    TaskMapper taskMapper;

    public UserModel toDTO(User user) {
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setUsername(user.getUsername());
        userModel.setPassword(user.getPassword());
        userModel.setTasks(convertToTaskModelList(user.getTasks()));
        return userModel;
    }

    private List<TaskModel> convertToTaskModelList(List<Task> tasks) {
        if (tasks == null) {
            return null;
        }
        return tasks.stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    public User toEntity(UserModel dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }
}