package com.postapi.user.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegisterResponse {
    private final Long id;
    private final String otp;
    private final String username;
    private final String message;
}
