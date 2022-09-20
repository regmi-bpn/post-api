package com.postapi.reaction.controller;

import com.postapi.reaction.constants.ReactionType;
import com.postapi.reaction.services.ReactionServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ReactionController {

    private final ReactionServices reactionService;


    @PostMapping("/{newsFeedId}/{userId}/{type}")
    @ResponseStatus(HttpStatus.OK)
    public void react(@PathVariable("newsFeedId") Long newsFeedId, @PathVariable("userId") Long userId, @PathVariable("type") ReactionType type) {
        reactionService.react(newsFeedId, userId, type);
    }

    @DeleteMapping("/{newsFeedId}/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void unReact(@PathVariable("newsFeedId") Long newsFeedId, @PathVariable("userId") Long userId) {
        reactionService.unReact(newsFeedId, userId);
    }

//    @GetMapping("/{newsFeedId}")
//    public ReactionDto.ReactionDtoBuilder getReactionByPost(@PathVariable("newsFeedId") Long newsFeedId) {
//        return reactionService.getReactionByPost(newsFeedId);
//    }



}
