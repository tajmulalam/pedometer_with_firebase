package com.stepproject.app.models;

import java.util.List;

/**
 * Created by tajmulalam on 7/11/18.
 */

public class TopUserScoreModel {
    private User user;
    private Scores score;

    public TopUserScoreModel(User user, Scores score) {
        this.user = user;
        this.score = score;
    }

    public TopUserScoreModel() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Scores getScore() {
        return score;
    }

    public void setScore(Scores score) {
        this.score = score;
    }
}
