package com.postapi.user.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "Password can not be blank")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()]).{8,32}$", message = "password must contain a number, uppercase, lowercase,  and special character must occur once and must be 8 to 32 character long")
    private String password;
    @NotBlank(message = "Conform password can not be blank")
    private String confirmPassword;
}
