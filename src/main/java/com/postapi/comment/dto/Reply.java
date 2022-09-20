package com.postapi.comment.dto;

import lombok.Data;

@Data
public class Reply{
    private Long id;
    private String username;
    private String replyMessage;
}
