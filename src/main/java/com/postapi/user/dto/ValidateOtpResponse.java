package com.postapi.user.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ValidateOtpResponse {
    private final String message = "OTP verified";
    private final Long id;
    private final String accessToken;
}
