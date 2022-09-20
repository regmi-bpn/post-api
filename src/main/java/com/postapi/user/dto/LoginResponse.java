package com.postapi.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private final String message = "Logged in successfully.";
    private final String token;
    private final UserInformationResponse userInformation;
}
