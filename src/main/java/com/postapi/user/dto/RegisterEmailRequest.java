package com.postapi.user.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterEmailRequest {
    @NotNull(message = "Email cannot be blank!!")
    @Pattern(regexp = "^[a-zA-Z0-9.]+[@][a-zA-Z]+[.][a-zA-Z]+$", message = "Email address not valid")
//    @Pattern(regexp = "^[a-zA-Z0-9]+[.]+[a-zA-Z0-9]+[@][a-zA-Z]+[.][a-zA-Z]+$", message = "Email address not valid")
    @Size(max = 255, message = "Email too long")
    private String email;
}
