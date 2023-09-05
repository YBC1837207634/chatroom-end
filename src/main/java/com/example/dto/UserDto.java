package com.example.dto;

import com.example.entity.User;
import lombok.Data;

@Data
public class UserDto extends User {
    private String token;
}
