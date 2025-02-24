package com.miniproject.wtlmini.service;

import com.miniproject.wtlmini.dto.user.CreateUserDto;
import com.miniproject.wtlmini.dto.user.LoginDto;
import com.miniproject.wtlmini.dto.user.UpdatePassDto;
import com.miniproject.wtlmini.entity.User;
import com.miniproject.wtlmini.mapping.GenericDtoMapper;
import com.miniproject.wtlmini.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    private final GenericDtoMapper mapper;

    public UserService(UserRepository userRepository, GenericDtoMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(CreateUserDto createUserDto) {
        User userToSave = mapper.toEntity(createUserDto, User.class);
        log.info("User to save: {}", userToSave);
        return userRepository.save(userToSave);
    }

    public User login(LoginDto loginDto) {
        if (loginDto == null) {
            throw new RuntimeException("LoginDto is null");
        }
        User user = userRepository.findByUsername(loginDto.getUsername());
        if (user != null && user.getPassword().equals(loginDto.getPassword())) {
            return user;
        } else throw new RuntimeException("User not found or password is incorrect");
    }

    public String updatePassword(UpdatePassDto updatePassDto) {
        User user = userRepository.findByUsername(updatePassDto.getUsername());
        if (user != null && user.getUsername().equals(updatePassDto.getUsername()) && user.getPassword().equals(updatePassDto.getOldPassword())) {
            user.setPassword(updatePassDto.getNewPassword());
            userRepository.save(user);
            return "Password updated successfully";
        } else {
            return "User not found or password is incorrect";
        }
    }
}
