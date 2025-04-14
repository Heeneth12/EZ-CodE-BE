package com.ezcode.user.controller;

import com.ezcode.user.dto.UserDTO;
import com.ezcode.user.dto.UserLoginRequest;
import com.ezcode.user.dto.UserRegistrationRequest;
import com.ezcode.user.service.UserService;
import com.ezcode.utils.ResponseResource;
import com.ezcode.utils.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * This method is used to register a new user with provided details (DTO version).
     *
     * @param request user registration data (email, fullName, password, userType)
     * @return response containing status message
     */
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResource<String> registerUser(@RequestBody UserRegistrationRequest request) {
        LOGGER.info("Came to register API with email: {}", request.getEmail());
        String response = userService.registerUser(request);
        return new ResponseResource<>(ResponseResource.R_CODE_OK, ResponseResource.RES_SUCCESS, response, Status.SUCCESS);
    }

    /**
     * Logs in a user with email and password.
     *
     * @param request login request data (email, password)
     * @return response containing login result message
     */
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResource<String> loginUser(@RequestBody UserLoginRequest request) {
        LOGGER.info("Login attempt for email: {}", request.getEmail());
        String result = userService.loginUser(request);
        return new ResponseResource<>(ResponseResource.R_CODE_OK, ResponseResource.RES_SUCCESS, result, Status.SUCCESS);
    }

    /**
     * Fetches user metadata (without password) using email.
     *
     * @param email email of the user to fetch
     * @return response containing user metadata or 404 if not found
     */
    @GetMapping(value = "/meta", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResource<UserDTO> getUserMeta(@RequestParam String email) {
        LOGGER.info("Fetching user meta for: {}", email);
        return userService.getUserMeta(email).map(meta -> new ResponseResource<>(ResponseResource.R_CODE_OK, ResponseResource.RES_SUCCESS, meta, Status.SUCCESS)).orElseGet(() -> new ResponseResource<>(ResponseResource.R_CODE_NOT_FOUND, "User Not Found", null, Status.FAILURE));
    }
}
