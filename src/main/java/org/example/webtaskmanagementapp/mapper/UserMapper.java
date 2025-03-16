package org.example.webtaskmanagementapp.mapper;

import org.example.webtaskmanagementapp.dto.TaskModel;
import org.example.webtaskmanagementapp.dto.UserModel;
import org.example.webtaskmanagementapp.entity.Task; // Correct Task entity import
import org.example.webtaskmanagementapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    TaskMapper taskMapper;

    public UserModel toDTO(User user) {
        return UserModel.builder()
                .id(user.getId())
                .username(user.getUsername())
                .tasks(convertToTaskModelList(user.getTasks()))
                .build();
    }

    private List<TaskModel> convertToTaskModelList(List<Task> tasks) {
        if(tasks == null){
            return null;
        }
        return tasks.stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    public User toEntity(UserModel dto) {
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .build();
    }
}