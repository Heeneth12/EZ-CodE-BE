package com.ezcode.auth.service;

import com.ezcode.auth.dto.AuthRegister;
import com.ezcode.auth.dto.AuthRequest;
import com.ezcode.auth.dto.AuthResponse;
import com.ezcode.auth.security.JwtTokenUtil;
import com.ezcode.user.entity.UserEntity;
import com.ezcode.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse login(AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            UserEntity user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            try {
                String accessToken = jwtTokenUtil.generateAccessToken(user);
                String refreshToken = jwtTokenUtil.generateRefreshToken(user);
                return new AuthResponse(accessToken, refreshToken);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error generating JWT tokens: " + e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Login failed: " + e.getMessage());
        }
    }



    public AuthResponse register(AuthRegister request) {
        // Check if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        UserEntity user =  new UserEntity();
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setUserType(request.getUserType());
        user.setRegisteredAt(LocalDateTime.now());
        user.setHashedPassword(passwordEncoder.encode(request.getPassword()));

        // Save user to database
        userRepository.save(user);

        // Generate tokens
        String accessToken = jwtTokenUtil.generateAccessToken(user);
        String refreshToken = jwtTokenUtil.generateRefreshToken(user);

        return new AuthResponse(accessToken, refreshToken);
    }
}