package com.ezcode.user.dto;

import com.ezcode.user.entity.UserType;
import lombok.Data;

@Data
public class UserDTO {
    private String email;
    private String fullName;
    private UserType userType;
}
