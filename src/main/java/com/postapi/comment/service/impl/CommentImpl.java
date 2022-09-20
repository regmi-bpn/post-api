package com.postapi.comment.service.impl;

import com.postapi.comment.dto.*;
import com.postapi.context.ContextHolderService;
import com.postapi.security.exception.RestException;
import com.postapi.constant.ErrorMessage;
import com.postapi.comment.entity.Comment;
import com.postapi.feed.entity.NewsFeed;
import com.postapi.user.entity.Users;
import com.postapi.comment.repository.CommentRepository;
import com.postapi.feed.repository.NewsFeedRepository;
import com.postapi.comment.service.CommentService;
import com.postapi.security.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ContextHolderService contextHolderService;
    @Autowired
    UserValidator userValidator;
    @Autowired
    NewsFeedRepository newsFeedRepository;

    @Override
    public AddCommentResponse addComment(AddCommentRequest request) {
        Users users = userValidator.validateUser(contextHolderService.getContext().getUserId(), contextHolderService.getContext().getUserType());
        validateNewsFeed(request.getNewsFeedId());
        Optional<NewsFeed> optionalNewsFeed = newsFeedRepository.getNewsFeed(request.getNewsFeedId());
        Comment comment = new Comment();
        comment.setUsers(users);
        comment.setComment(request.getComment());
        comment.setNewsFeed(optionalNewsFeed.get());
        if (request.getReplyId() == null) {

            Comment addComment = commentRepository.save(comment);
            return prepareToAddCommentResponse(optionalNewsFeed.get(), users, addComment);
        } else {
            comment.set_reply(true);
            comment.setReplyId(request.getReplyId());
            Comment addComment = commentRepository.save(comment);
            return prepareToAddCommentResponse(optionalNewsFeed.get(), users, addComment);
        }
//        comment.set_reply(true);


    }

    @Override
    public AddCommentResponse updateComment(UpdateCommentRequest request) {
//        Users users = userValidator.validateUser(contextHolderService.getContext().getUserId(), contextHolderService.getContext().getUserType());
        Comment comment = getCommentResponse(request.getId());
        comment.setComment(request.getComment());
        commentRepository.save(comment);
        return updateCommentResponse(comment);

    }

    @Override
    public List<CommentResponse> getAllComment() {
        List<Comment> commentList = commentRepository.findAllComment();
        List<CommentResponse> response = new ArrayList<>();
        commentList.forEach(comment -> {
            response.add(prepareCommentList(comment));
        });
        return response;
    }

    @Transactional
    @Override
    public DeleteCommentResponse deleteComment(Long id) {
        validateComment(id);
        commentRepository.deleteComment(id);
        return DeleteCommentResponse.builder().message("comment of id:- " + id + " deleted").build();
    }

    private CommentResponse prepareCommentList(Comment comment) {
        if (comment == null) {
            return null;
        }
        return prepareReplyResponse(comment);
    }

    private AddCommentResponse updateCommentResponse(Comment comment) {
        return AddCommentResponse.builder().newsFeed(comment.getNewsFeed().getMessage()).commentId(comment.getId()).comment(comment.getComment()).username(comment.getUsers().getUsername()).build();
    }

    private Comment getCommentResponse(Long id) {
        Optional<Comment> optionalComment = commentRepository.getCommentById(id);
        validateComment(id);
        return optionalComment.get();
    }

    private void validateComment(Long id) {
        Optional<Comment> optionalComment = commentRepository.getCommentById(id);
        if (optionalComment.isEmpty()) {
            throw new RestException(ErrorMessage.COMMENT_ID_NOT_VALID);
        }
    }

    private void validateNewsFeed(Long id) {
        Optional<NewsFeed> optionalNewsFeed = newsFeedRepository.getNewsFeed(id);
        if (optionalNewsFeed.isEmpty()) {
            throw new RestException(ErrorMessage.INVALID_NEWSFEED_ID);
        }
    }

    private AddCommentResponse prepareToAddCommentResponse(NewsFeed newsFeed, Users users, Comment comment) {
        return AddCommentResponse.builder().newsFeed(newsFeed.getMessage()).commentId(comment.getId()).
                comment(comment.getComment()).username(users.getUsername()).isReply(comment.is_reply()).build();
    }

    private CommentResponse prepareReplyResponse(Comment comment) {
        return CommentResponse.builder().newsFeedData(prepareNewsFeedData(comment)).commentData(prepareCommentData(comment)).build();
    }

    private NewsFeedResponseForComment prepareNewsFeedData(Comment comment) {
        return NewsFeedResponseForComment.builder().newsFeedId(comment.getNewsFeed().getId()).newsFeed(comment.getNewsFeed().getMessage()).build();
    }

    private CommentData prepareCommentData(Comment comment){
        return CommentData.builder().commentId(comment.getId()).userId(comment.getUsers().getId()).username(comment.getUsers().getUsername()).
                commentMessage(comment.getComment()).replyCommentData(prepareReply(comment.getId())).build();
    }

    private List<Reply> prepareReply(Long id) {
        List<Comment> replyList = commentRepository.findReplyList(id);
        List<Reply> replies = new ArrayList<>();
        replyList.forEach(response -> {
            Reply reply = new Reply();
            reply.setId(response.getId());
            reply.setUsername(response.getUsers().getUsername());
            reply.setReplyMessage(response.getComment());
            replies.add(reply);
        });
        return replies;
    }
}
