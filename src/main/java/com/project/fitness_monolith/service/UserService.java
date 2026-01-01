package com.project.fitness_monolith.service;

import com.project.fitness_monolith.dto.RegisterRequest;
import com.project.fitness_monolith.dto.UserResponse;
import com.project.fitness_monolith.entity.User;
import com.project.fitness_monolith.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse register(RegisterRequest registerRequest) {
//        User user = new User(null,
//                registerRequest.getEmail(),
//                registerRequest.getPassword(),
//                registerRequest.getFirstName(),
//                registerRequest.getLastName(),
//                LocalDateTime.now(),
//                LocalDateTime.now(),
//                List.of(),
//                List.of());

        User user = User.builder()
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .build();

        User savedUser = userRepository.save(user);

        UserResponse userResponse = new UserResponse(savedUser.getId(), savedUser.getEmail(), savedUser.getPassword(), savedUser.getFirstName(), savedUser.getLastName());

        return userResponse;
    }

}
