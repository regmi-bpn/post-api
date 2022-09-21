package com.postapi.comment.controller;


import com.postapi.comment.dto.*;
import com.postapi.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


import java.util.List;

import static com.postapi.constant.Route.*;

@RestController
public class CommentController {

    private final CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = ADD_COMMENT)
    public AddCommentResponse addComment(@Valid @RequestBody AddCommentRequest request) {
        return commentService.addComment(request);
    }

    @PutMapping(value = UPDATE_COMMENT)
    public AddCommentResponse updateComment(@Valid @RequestBody UpdateCommentRequest request) {
        return commentService.updateComment(request);
    }

    @GetMapping(value = GET_COMMENT)
    public List<CommentResponse> getAllComment() {
        return commentService.getAllComment();
    }

    @DeleteMapping(value = DELETE_COMMENT)
    public DeleteCommentResponse deleteComment(@Valid @PathVariable Long id) {
        return commentService.deleteComment(id);
    }
}
