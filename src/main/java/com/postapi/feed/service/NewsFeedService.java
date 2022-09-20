package com.postapi.feed.service;

import com.postapi.feed.dto.*;

import java.util.List;

public interface NewsFeedService {
    PostNewsFeedResponse postNewsFeed(PostNewsFeedRequest request);

    NewsFeedResponse updateNewsFeed(UpdateNewsFeedRequest request);

    UsersDetailsResponse getUserNewsFeed();

    List<NewsFeedDetailsResponse> getAllNewsFeed();

    DeleteNewsFeedResponse deleteNewsFeed(Long id);

}
