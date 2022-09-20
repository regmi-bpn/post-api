package com.postapi.reaction.repository;


import com.postapi.reaction.entity.Reaction;
import com.postapi.reaction.entity.ReactionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, ReactionId> {

//    Long countByIdPostIdAndType(Long newsFeedId, ReactionType reactionType);

//    @Query("SELECT r.users FROM Reaction r WHERE r.id.newsFeedId=?1 and r.type =?2")
//    List<Users> findUserByIdPostIdAndType(Long newsFeedId, ReactionType reactionType);
}
