package com.ezcode.auth.controller;

import com.ezcode.auth.dto.AuthRegister;
import com.ezcode.auth.dto.AuthRequest;
import com.ezcode.auth.dto.AuthResponse;
import com.ezcode.auth.security.JwtTokenUtil;
import com.ezcode.auth.service.AuthService;
import com.ezcode.user.entity.UserEntity;
import com.ezcode.user.repository.UserRepository;
import com.ezcode.utils.ResponseResource;
import com.ezcode.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    /**
     * Authenticates a user with email and password credentials.
     *
     * @param request contains email and password
     * @return ResponseResource with access and refresh tokens
     */
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResource<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request);
        return new ResponseResource<>(ResponseResource.R_CODE_OK, ResponseResource.RES_SUCCESS, response, Status.SUCCESS);
    }

    /**
     * Registers a new user using email, password, and other details.
     *
     * @param authRegister user registration payload
     * @return ResponseResource with access and refresh tokens
     */
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResource<AuthResponse> register(@RequestBody AuthRegister authRegister) {
        AuthResponse response = authService.register(authRegister);
        return new ResponseResource<>(ResponseResource.R_CODE_OK, ResponseResource.RES_SUCCESS, response, Status.SUCCESS);
    }

    /**
     * Issues a new access token using a valid refresh token.
     *
     * @param request a map containing the refreshToken
     * @return ResponseResource with new access token and same refresh token
     */
    @PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResource<AuthResponse> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        if (jwtTokenUtil.validateToken(refreshToken)) {
            String email = jwtTokenUtil.getUsernameFromToken(refreshToken);
            UserEntity user = userRepository.findByEmail(email).orElseThrow();
            String newAccessToken = jwtTokenUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(newAccessToken, refreshToken);
            return new ResponseResource<>(ResponseResource.R_CODE_OK, ResponseResource.RES_SUCCESS, response, Status.SUCCESS);
        } else {
            return new ResponseResource<>(ResponseResource.R_CODE_UNAUTH_ERROR, "Invalid refresh token", null, Status.FAILURE);
        }
    }
}
