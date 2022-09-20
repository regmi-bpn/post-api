package com.postapi.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentResponse {
    private NewsFeedResponseForComment newsFeedData;
    private CommentData commentData;
}
