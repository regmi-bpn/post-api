package com.postapi.feed.controller;


import com.postapi.feed.dto.*;
import com.postapi.feed.service.NewsFeedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.postapi.constant.Route.*;

@Slf4j
@RestController
public class NewsFeedController {

    @Autowired
    NewsFeedService newsFeedService;

    @PostMapping(value = POST_NEWS_FEED)
    public PostNewsFeedResponse postNewsFeed(@Valid @RequestBody PostNewsFeedRequest request) {
        log.info("Post newsFeed:: {}", request);
        return newsFeedService.postNewsFeed(request);
    }

    @PutMapping(value = UPDATE_NEWS_FEED)
    public NewsFeedResponse updateNewsFeed(@Valid @RequestBody UpdateNewsFeedRequest request) {
        log.info("Update newsFeed:: {}", request);
        return newsFeedService.updateNewsFeed(request);
    }

    @GetMapping(value = GET_USER_NEWS_FEED)
    public UsersDetailsResponse getUserNewsFeed() {
        log.info("User newsFeeds");
        return newsFeedService.getUserNewsFeed();
    }

    @GetMapping(value = USER_NEWS_FEED)
    public List<NewsFeedDetailsResponse> getAllNewsFeed() {
        log.info("newsFeed List");
        return newsFeedService.getAllNewsFeed();
    }

    @DeleteMapping(value = DELETE_NEWS_FEED)
    public DeleteNewsFeedResponse deleteNewsFeed(@Valid @PathVariable Long id) {
        log.info("delete newsFeed");
        return newsFeedService.deleteNewsFeed(id);
    }
}
