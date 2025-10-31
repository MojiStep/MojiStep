package com.mojistep.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String username;
    private String fullName;
    private String password;
}
