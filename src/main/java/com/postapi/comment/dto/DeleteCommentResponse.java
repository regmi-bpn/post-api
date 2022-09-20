package com.postapi.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeleteCommentResponse {
    private String message;
}
