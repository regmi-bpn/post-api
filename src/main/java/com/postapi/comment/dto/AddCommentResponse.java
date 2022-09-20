package com.postapi.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddCommentResponse {
    private String newsFeed;
    private String username;
    private Long commentId;
    private String comment;
    private boolean isReply;

}
