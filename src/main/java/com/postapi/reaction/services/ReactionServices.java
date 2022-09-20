package com.postapi.reaction.services;

import com.postapi.reaction.constants.ReactionType;

public interface ReactionServices {

    void react(Long newsFeedId, Long userId, ReactionType type);

    void unReact(Long newsFeedId, Long userId);

//    HashMap<String, Long> getReactionCount(Long newsFeedId);

//    ReactionDto getReactionByPost(Long newsFeedId);
}
