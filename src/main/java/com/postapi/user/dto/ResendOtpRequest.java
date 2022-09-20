package com.postapi.user.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ResendOtpRequest {
    @NotBlank(message = "user name can not be blank")
    @Size(message = "user name size too long")
    private String username;
}
