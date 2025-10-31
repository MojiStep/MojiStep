package com.mojistep.auth;

import lombok.Data;

@Data
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    public TokenResponse(String a, String r){
        this.accessToken = a;
        this.refreshToken = r;
    }
}
