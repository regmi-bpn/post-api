package com.postapi.feed.service.impl;


import com.postapi.context.ContextHolderService;
import com.postapi.security.exception.RestException;
import com.postapi.constant.ErrorMessage;
import com.postapi.feed.entity.NewsFeed;
import com.postapi.user.entity.Users;
import com.postapi.feed.dto.*;
import com.postapi.feed.repository.NewsFeedRepository;
import com.postapi.user.repository.UserRepository;
import com.postapi.feed.service.NewsFeedService;
import com.postapi.security.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewsFeedImpl implements NewsFeedService {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private ContextHolderService contextHolderService;

    @Autowired
    private NewsFeedRepository newsFeedRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PostNewsFeedResponse postNewsFeed(PostNewsFeedRequest request) {
        Users users = userValidator.validateUser(contextHolderService.getContext().getUserId(), contextHolderService.getContext().getUserType());
        validatePost(request);
        NewsFeed newsFeed = newsFeedRepository.save(prepareNewsFeed(request, users));
        return newsFeedResponse(request, users);
    }

    @Override
    public NewsFeedResponse updateNewsFeed(UpdateNewsFeedRequest request) {
        Users users = userValidator.validateUser(contextHolderService.getContext().getUserId(), contextHolderService.getContext().getUserType());
        Optional<NewsFeed> valNewsFeed = newsFeedRepository.getNewsFeedById(request.getId(), request.getNewsFeedId());
        if (valNewsFeed.isPresent()) {
            NewsFeed newsFeed = valNewsFeed.get();
            newsFeedRepository.save(updateNewsFeed(newsFeed, request));
            return NewsFeedResponse.builder().id(valNewsFeed.get().getId()).message(valNewsFeed.get().getMessage()).build();
        } else throw new RestException(ErrorMessage.NOT_VALID_CREDENTIAL);
    }

    @Override
    public UsersDetailsResponse getUserNewsFeed() {
        Users users = userValidator.validateUser(contextHolderService.getContext().getUserId(), contextHolderService.getContext().getUserType());
        return prepareUserDetailsResponse(users);
    }

    @Override
    public List<NewsFeedDetailsResponse> getAllNewsFeed() {
        List<NewsFeed> newsFeedList = newsFeedRepository.findAll();
        List<NewsFeedDetailsResponse> response = new ArrayList<>();
        newsFeedList.forEach(newsFeed -> {
            response.add((prepareNewsFeedList(newsFeed)));
        });
        return response;
    }

    @Transactional
    @Override
    public DeleteNewsFeedResponse deleteNewsFeed(Long id) {
        validateNewsFeed(id);
        newsFeedRepository.deleteNewsFeed(id);
        return DeleteNewsFeedResponse.builder().newsFeedId(id).message("newsfeed of "+id+" deleted").build();
    }
    private void validateNewsFeed(Long id){
        Optional<NewsFeed> optionalNewsFeed= newsFeedRepository.getNewsFeed(id);
        if (optionalNewsFeed.isEmpty()){
            throw new RestException(ErrorMessage.INVALID_NEWSFEED_ID);
        }
    }

    private NewsFeedDetailsResponse prepareNewsFeedList(NewsFeed newsFeed) {
        if (newsFeed == null) {
            return null;
        }
        return NewsFeedDetailsResponse.builder().id(newsFeed.getUsers().getId()).username(newsFeed.getUsers().getUsername()).messageId(newsFeed.getId()).message(newsFeed.getMessage()).build();

    }

    private UsersDetailsResponse prepareUserDetailsResponse(Users users) {

        Users userData = userRepository.findUserById(users.getId());
        return UsersDetailsResponse.builder().id(userData.getId()).username(userData.getUsername()).newsFeedList(prepareNewsFeedResponse(userData)).build();
    }

    public List<NewsFeedResponseList> prepareNewsFeedResponse(Users users) {
        List<NewsFeed> newsFeedList = newsFeedRepository.getNewsFeedOfUser(users.getId());
        if (newsFeedList.isEmpty()) {
            throw new RestException(ErrorMessage.USER_POST_NOT_AVAILABLE);
        }
        List<NewsFeedResponseList> newsFeedResponseLists = new ArrayList<>();
        newsFeedList.forEach(newsFeed -> {
            NewsFeedResponseList newsFeedResponseList = new NewsFeedResponseList();
            newsFeedResponseList.setMessageId(newsFeed.getId());
            newsFeedResponseList.setMessage(newsFeed.getMessage());
            newsFeedResponseLists.add(newsFeedResponseList);
        });
        return newsFeedResponseLists;

    }

    private NewsFeed updateNewsFeed(NewsFeed newsFeed, UpdateNewsFeedRequest request) {
        newsFeed.setMessage(request.getMessage());
        return newsFeed;
    }

    private PostNewsFeedResponse newsFeedResponse(PostNewsFeedRequest request, Users users) {
        return PostNewsFeedResponse.builder().username(users.getUsername()).message(request.getMessage()).build();

    }

    private NewsFeed prepareNewsFeed(PostNewsFeedRequest request, Users users) {
        NewsFeed newsFeed = new NewsFeed();
        newsFeed.setUsers(users);
        newsFeed.setMessage(request.getMessage());
        return newsFeed;
    }

    private void validatePost(PostNewsFeedRequest request) {
        if (request.getMessage().isEmpty()) {
            throw new RestException(ErrorMessage.MESSAGE_EMPTY);
        }
    }

}
