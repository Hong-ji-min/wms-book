package com.sh.user.model.dto;

import com.sh.user.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int userId;
    private String userName;
    private String password;
//    private UserRole role;
    private UserRole role;
    private LocalDateTime createdAt;
}
