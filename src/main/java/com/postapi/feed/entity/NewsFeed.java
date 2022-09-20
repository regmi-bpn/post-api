package com.postapi.feed.entity;

import com.postapi.user.entity.Users;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "news_feed")
public class NewsFeed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;

}
