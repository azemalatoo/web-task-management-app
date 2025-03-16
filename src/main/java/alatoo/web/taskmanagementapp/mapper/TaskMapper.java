package alatoo.web.taskmanagementapp.mapper;

import alatoo.web.taskmanagementapp.entity.Task;
import alatoo.web.taskmanagementapp.dto.TaskModel;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {


        public TaskModel toDTO(Task task) {
            if (task == null) {
                return null;
            }

            TaskModel taskModel = new TaskModel();
            taskModel.setId(task.getId());
            taskModel.setTitle(task.getTitle());
            taskModel.setDescription(task.getDescription());
            taskModel.setStatus(task.getStatus());

                taskModel.setUserId(task.getUser().getId());

            return taskModel;
        }


    public Task toEntity(TaskModel dto) {
        Task task = new Task();
        task.setId(dto.getId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        return task;
    }
}
