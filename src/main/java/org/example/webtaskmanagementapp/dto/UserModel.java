package org.example.webtaskmanagementapp.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserModel {
    private Long id;
    private String username;
    private List<TaskModel> tasks;
}