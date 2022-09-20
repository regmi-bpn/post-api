package com.postapi.user.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateAdminRequest {

    private Long id;
    private String roles;
}
