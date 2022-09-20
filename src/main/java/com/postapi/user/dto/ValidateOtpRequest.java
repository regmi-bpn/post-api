package com.postapi.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ValidateOtpRequest {
    @NotNull(message = "id cannot be blank!!")
    private Long id;

    @NotBlank(message = "OTP is mandatory")
    @Size(min = 6, max = 6, message = "OTP is mandatory")
    private String otp;
}
