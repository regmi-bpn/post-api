package com.postapi.reaction.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ReactionId implements Serializable {
    @Column(name = "feed_id")
    private Long newsFeedId;
    @Column(name = "user_id")
    private Long userId;
}
