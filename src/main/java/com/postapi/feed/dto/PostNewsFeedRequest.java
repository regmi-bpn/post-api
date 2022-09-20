package com.postapi.feed.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PostNewsFeedRequest {
    @NotNull(message = "id cannot be blank")
    private Long id;

    @NotNull(message = "message cannot be blank")
    private String message;
}
