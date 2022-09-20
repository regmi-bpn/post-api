package com.postapi.feed.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeleteNewsFeedResponse {
    private Long newsFeedId;
    private String message;
}
