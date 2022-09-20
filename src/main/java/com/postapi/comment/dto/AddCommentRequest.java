package com.postapi.comment.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddCommentRequest {

    @NotNull(message = "newsFeed id cannot be null")
    private Long newsFeedId;

    @NotNull(message = "comment cannot be null")
    private String comment;

    private Long replyId;

}
