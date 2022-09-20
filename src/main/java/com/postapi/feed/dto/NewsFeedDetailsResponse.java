package com.postapi.feed.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NewsFeedDetailsResponse {
    private Long id;
    private String username;
    private Long messageId;
    private String message;
}
