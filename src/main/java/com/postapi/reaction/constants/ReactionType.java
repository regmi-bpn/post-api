package com.postapi.reaction.constants;

public enum ReactionType {
    LIKE("like"), HAHA("haha"), SAD("sad"), WOW("wow"), LOVE("love");
    private String reaction;

    ReactionType(String reaction) {
        this.reaction = reaction;
    }

    public String getReaction() {
        return reaction;
    }
}
