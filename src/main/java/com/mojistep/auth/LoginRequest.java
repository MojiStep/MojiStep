package com.mojistep.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username; // email or username
    private String password;
}
