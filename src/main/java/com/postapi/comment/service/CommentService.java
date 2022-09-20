package com.postapi.comment.service;


import com.postapi.comment.dto.*;

import java.util.List;

public interface CommentService {
    AddCommentResponse addComment(AddCommentRequest request);

    AddCommentResponse updateComment(UpdateCommentRequest request);

    List<CommentResponse> getAllComment();

    DeleteCommentResponse deleteComment(Long id);

}
