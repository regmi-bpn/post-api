package com.postapi.feed.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NewsFeedResponse {
    private Long id;
    private String message;
}
