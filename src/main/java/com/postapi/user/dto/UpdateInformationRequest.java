package com.postapi.user.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateInformationRequest {
    @NotNull(message = "address cannot be null")
    private String address;

    @NotNull(message = "firstname cannot be null")
    private String firstName;

    @NotNull(message = "lastname cannot be null")
    private String lastName;

    @NotNull(message = "phone number cannot be null")
    private String phoneNumber;
}
