package com.postapi.reaction.entity;


import com.postapi.feed.entity.NewsFeed;
import com.postapi.user.entity.Users;
import com.postapi.reaction.constants.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "reaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reaction {

    public Reaction(NewsFeed newsFeed, Users users, ReactionType type) {
        this.id = new ReactionId(newsFeed.getId(), users.getId());
        this.newsFeed = newsFeed;
        this.users = users;
        this.type = type;
    }

    @EmbeddedId
    private ReactionId id;

    @Enumerated(EnumType.STRING)
    private ReactionType type;

    @ManyToOne
    @MapsId("feed_id")
    @JoinColumn(name = "feed_id")
    private NewsFeed newsFeed;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private Users users;

}
