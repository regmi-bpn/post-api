package com.postapi.comment.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CommentData {
    private Long commentId;
    private Long userId;
    private String username;
    private String commentMessage;
    private List<Reply> replyCommentData;

}
