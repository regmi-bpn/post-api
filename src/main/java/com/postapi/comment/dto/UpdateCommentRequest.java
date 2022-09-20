package com.postapi.comment.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateCommentRequest {
    @NotNull(message = "comment id cannot be null")
    private Long id;

    @NotNull(message = "comment cannot be null")
    private String comment;
}
