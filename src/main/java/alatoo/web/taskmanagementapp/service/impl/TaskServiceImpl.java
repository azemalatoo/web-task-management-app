package alatoo.web.taskmanagementapp.service.impl;

import alatoo.web.taskmanagementapp.dto.TaskModel;
import alatoo.web.taskmanagementapp.entity.Task;
import alatoo.web.taskmanagementapp.entity.User;
import alatoo.web.taskmanagementapp.enums.TaskStatus;
import alatoo.web.taskmanagementapp.exception.NotFoundException;
import alatoo.web.taskmanagementapp.mapper.TaskMapper;
import alatoo.web.taskmanagementapp.repo.TaskRepository;
import alatoo.web.taskmanagementapp.repo.UserRepository;
import alatoo.web.taskmanagementapp.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<TaskModel> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskModel getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Task not found with id %s",  id)));
        return taskMapper.toDTO(task);
    }

    @Override
    public TaskModel createTask(TaskModel taskModel) {
        Task task = taskMapper.toEntity(taskModel);
        task.setStatus(TaskStatus.PENDING);
        task.setUser(getUserById(taskModel.getUserId()));
        task = taskRepository.save(task);
        return taskMapper.toDTO(task);
    }

    @Override
    public TaskModel updateTask(Long id, TaskModel taskModel) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setTitle(taskModel.getTitle());
            existingTask.setDescription(taskModel.getDescription());
            existingTask.setStatus(taskModel.getStatus());
            existingTask.setUser(getUserById(taskModel.getUserId()));
            taskRepository.save(existingTask);
            return taskMapper.toDTO(existingTask);
        } else {
            throw new NotFoundException(String.format("Task not found with id %s",  id));
        }
    }

    @Override
    public void deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new NotFoundException(String.format("Task not found with id %s",  id));
        }
    }

    @Override
    public List<TaskModel> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId).stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User not found with id %s", userId)));
    }
}
