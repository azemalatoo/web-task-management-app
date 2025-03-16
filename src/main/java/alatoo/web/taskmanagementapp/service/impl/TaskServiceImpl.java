package alatoo.web.taskmanagementapp.service.impl;

import alatoo.web.taskmanagementapp.dto.TaskModel;
import alatoo.web.taskmanagementapp.entity.Task;
import alatoo.web.taskmanagementapp.exception.NotFoundException;
import alatoo.web.taskmanagementapp.mapper.TaskMapper;
import alatoo.web.taskmanagementapp.repo.TaskRepository;
import alatoo.web.taskmanagementapp.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
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
}
