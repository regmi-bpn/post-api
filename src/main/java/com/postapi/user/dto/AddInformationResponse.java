package com.postapi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddInformationResponse<T> {
    private final String message;
    private final T data;
}
