package com.postapi.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NewsFeedResponseForComment {
    private Long newsFeedId;
    private String newsFeed;
}
