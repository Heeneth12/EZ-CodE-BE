package com.ezcode.user.service;

import com.ezcode.user.dto.UserDTO;
import com.ezcode.user.dto.UserLoginRequest;
import com.ezcode.user.dto.UserRegistrationRequest;
import com.ezcode.user.entity.UserEntity;
import com.ezcode.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public String registerUser(UserRegistrationRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already registered";
        }

//        String hashed = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt());
//        UserEntity newUser = UserEntity.builder()
//                .email(request.getEmail())
//                .fullName(request.getFullName())
//                .hashedPassword(hashed)
//                .userType(request.getUserType())
//                .registeredAt(LocalDateTime.now())
//                .build();
//
//        userRepository.save(newUser);
        return "User registered successfully";
    }

    public String loginUser(UserLoginRequest request) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isEmpty()) return "Invalid credentials";

        UserEntity user = userOpt.get();
        return "";
    }

    public Optional<UserDTO> getUserMeta(String email) {
        return userRepository.findByEmail(email).map(user -> {
            UserDTO dto = new UserDTO();
            dto.setEmail(user.getEmail());
            dto.setFullName(user.getFullName());
            dto.setUserType(user.getUserType());
            return dto;
        });
    }

}
