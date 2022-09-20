package com.postapi.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class LoginRequest {

    @NotNull(message = "user name can not be blank")
    private String username;
    @NotBlank(message = "Password can not be blank")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()]).{8,32}$", message = "password must contain a number, uppercase, lowercase,  and special character must occur once and must be 8 to 32 character long")
    private String password;

    @Override
    public String toString() {
        return super.toString().concat("userName='" + username + '\'');
    }
}
