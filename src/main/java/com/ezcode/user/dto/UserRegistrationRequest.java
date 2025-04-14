package com.ezcode.user.dto;

import com.ezcode.user.entity.UserType;
import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String email;
    private String fullName;
    private String password;
    private UserType userType;
}
