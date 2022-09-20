package com.postapi.user.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddInformationRequest extends UpdateInformationRequest {
    @NotBlank(message = "Password can not be blank")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()]).{8,32}$", message = "password must contain a number, uppercase, lowercase,  and special character must occur once and must be 8 to 32 character long")
    private String password;
    @NotBlank(message = "Conform password can not be blank")
    private String confirmPassword;

}
