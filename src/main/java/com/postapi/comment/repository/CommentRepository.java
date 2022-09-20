package com.postapi.comment.repository;

import com.postapi.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select comment from Comment comment where comment.id=:id")
    Optional<Comment> getCommentById(Long id);

    @Modifying
    @Query("delete from Comment comment where comment.id=:id")
    void  deleteComment(Long id);
//    new com.intern.votingSystem.dto.VoteCountDTO

    @Query("select comment from Comment comment where comment.replyId=:id")
    List<Comment> findReplyList(Long id);

//    select * from comment comment where comment.is_reply=false order by comment.id Desc;
    @Query("select comment from Comment comment where comment.is_reply=false order by comment.id DESC")
    List<Comment> findAllComment();
}
