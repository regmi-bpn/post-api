package com.postapi.feed.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateNewsFeedRequest {
    @NotNull(message = "id cannot be blank")
    private Long id;

    @NotNull(message = "newsFeed id cannot be blank")
    private Long newsFeedId;

    @NotNull(message = "message cannot be blank")
    private String message;
}
