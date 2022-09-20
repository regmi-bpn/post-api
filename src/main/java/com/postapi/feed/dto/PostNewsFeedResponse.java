package com.postapi.feed.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostNewsFeedResponse {
    private String username;
    private String message;
}
