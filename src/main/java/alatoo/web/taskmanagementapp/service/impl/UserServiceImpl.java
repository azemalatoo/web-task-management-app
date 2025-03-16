package alatoo.web.taskmanagementapp.service.impl;

import alatoo.web.taskmanagementapp.dto.UserModel;
import alatoo.web.taskmanagementapp.entity.User;
import alatoo.web.taskmanagementapp.mapper.UserMapper;
import alatoo.web.taskmanagementapp.repo.UserRepository;
import alatoo.web.taskmanagementapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserModel getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
        return userMapper.toDTO(user);
    }

    @Override
    public UserModel createUser(UserModel userModel) {
        User user = userMapper.toEntity(userModel);
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Override
    public UserModel updateUser(Long id, UserModel userModel) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setUsername(userModel.getUsername());
            existingUser.setPassword(userModel.getPassword());
            userRepository.save(existingUser);
            return userMapper.toDTO(existingUser);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }
}
