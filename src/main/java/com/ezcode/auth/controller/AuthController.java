package com.ezcode.auth.controller;

import com.ezcode.auth.dto.AuthRequest;
import com.ezcode.auth.dto.AuthResponse;
import com.ezcode.auth.security.JwtTokenUtil;
import com.ezcode.auth.service.AuthService;
import com.ezcode.user.entity.UserEntity;
import com.ezcode.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refresh")
    public AuthResponse refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        if (jwtTokenUtil.validateToken(refreshToken)) {
            String email = jwtTokenUtil.getUsernameFromToken(refreshToken);
            UserEntity user = userRepository.findByEmail(email).orElseThrow();
            String newAccessToken = jwtTokenUtil.generateAccessToken(user);
            return new AuthResponse(newAccessToken, refreshToken); // Reuse refresh token
        } else {
            throw new RuntimeException("Invalid refresh token");
        }
    }

}
