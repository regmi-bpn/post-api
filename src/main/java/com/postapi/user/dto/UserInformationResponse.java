package com.postapi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInformationResponse {
    private Long id;
    private String username;
    private String phoneNumber;
    private String address;
}
