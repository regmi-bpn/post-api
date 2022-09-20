package com.postapi.reaction.services;


import com.postapi.feed.entity.NewsFeed;
import com.postapi.user.entity.Users;
import com.postapi.reaction.constants.ReactionType;
import com.postapi.reaction.entity.Reaction;
import com.postapi.reaction.entity.ReactionId;
import com.postapi.reaction.repository.ReactionRepository;
import com.postapi.feed.repository.NewsFeedRepository;
import com.postapi.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReactionImpl implements ReactionServices {
    private final NewsFeedRepository newsFeedRepository;
    private final UserRepository userRepository;
    private final ReactionRepository reactionRepository;


    @Override
    public void react(Long newsFeedId, Long userId, ReactionType type) {
        NewsFeed newsFeed = newsFeedRepository.findById(newsFeedId).orElseThrow(() -> new RuntimeException("ERROR!!!!"));
        Users users = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("ERROR!!!!"));
        reactionRepository.save(new Reaction(newsFeed, users, type));
    }

    @Override
    public void unReact(Long newsFeedId, Long userId) {
        newsFeedRepository.findById(newsFeedId).orElseThrow(() -> new RuntimeException("ERROR!!!!"));
        userRepository.findById(userId).orElseThrow(() -> new RuntimeException("ERROR!!!!"));
        reactionRepository.deleteById(new ReactionId(newsFeedId, userId));
    }

//    @Override
//    public HashMap<String, Long> getReactionCount(Long newsFeedId) {
//        HashMap<String, Long> reactionMap = new HashMap<>();
//        Long totalReaction = 0L;
//        for (ReactionType type : ReactionType.values()) {
//            Long count = reactionRepository.countByIdPostIdAndType(newsFeedId, type);
//            totalReaction += count;
//            reactionMap.put(type.getReaction(), count);
//        }
//        reactionMap.put("total", totalReaction);
//        return reactionMap;
//    }

//    @Override
//    public ReactionDto getReactionByPost(Long newsFeedId) {
//        HashMap<String, List<UserDto>> reactionMap = new HashMap<>();
//        ReactionDto reaction = null;
//        for (ReactionType type : ReactionType.values()) {
//            List<Users> users = reactionRepository.findUserByIdNewsFeedIdAndType(newsFeedId, type);
//
//            reaction = ReactionDto.builder().reaction(reactionMap).build();
//
////            reactionMap.put(type.getReaction(), users.stream().map(u -> modelMapper.map(u, UserDto.class)).collect(Collectors.toList()));
//        }
////        return new ReactionDto(reactionMap);
//
//        return reaction;
//    }


}
