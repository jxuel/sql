package com.spl.splserver.POJO;


import java.util.ArrayList;
import java.util.Date;

public class LearnState {
    private Integer score;
    private Float repeatChance;
    private Float lastScore;
    private ArrayList<String> lastAnswer;
    private Date reviewedAt;
    private Long repeatedInterval;
    private Date repeatAt;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Float getRepeatChance() {
        return repeatChance;
    }

    public void setRepeatChance(Float repeatChance) {
        this.repeatChance = repeatChance;
    }

    public Float getLastScore() {
        return lastScore;
    }

    public void setLastScore(Float lastScore) {
        this.lastScore = lastScore;
    }

    public ArrayList<String> getLastAnswer() {
        return lastAnswer;
    }

    public void setLastAnswer(ArrayList<String> lastAnswer) {
        this.lastAnswer = lastAnswer;
    }

    public Date getReviewedAt() {
        return reviewedAt;
    }

    public void setReviewedAt(Date reviewedAt) {
        this.reviewedAt = reviewedAt;
    }

    public Long getRepeatedInterval() {
        return repeatedInterval;
    }

    public void setRepeatedInterval(Long repeatedInterval) {
        this.repeatedInterval = repeatedInterval;
    }

    public Date getRepeatAt() {
        return repeatAt;
    }

    public void setRepeatAt(Date repeatAt) {
        this.repeatAt = repeatAt;
    }

    @Override
    public String toString() {
        return "LearnState{" +
                "score=" + score +
                ", repeatChance=" + repeatChance +
                ", lastScore=" + lastScore +
                ", lastAnswer=" + lastAnswer +
                ", reviewedAt=" + reviewedAt +
                ", repeatedInterval=" + repeatedInterval +
                ", repeatAt=" + repeatAt +
                '}';
    }
}
