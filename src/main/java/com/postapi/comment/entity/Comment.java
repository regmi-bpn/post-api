package com.postapi.comment.entity;


import com.postapi.feed.entity.NewsFeed;
import com.postapi.user.entity.Users;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment")
    private String comment;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;

    @Column(name = "is_reply")
    private boolean is_reply = false;

    @Column(name = "reply_id")
    private Long replyId;

    @OneToOne
    @JoinColumn(name = "newsFeed_id", referencedColumnName = "id")
    private NewsFeed newsFeed;
}
