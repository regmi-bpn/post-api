package com.postapi.feed.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class UsersDetailsResponse {
    private Long id;
    private String username;
    private List<NewsFeedResponseList> newsFeedList;
}
