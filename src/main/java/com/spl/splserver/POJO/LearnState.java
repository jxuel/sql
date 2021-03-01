package com.spl.splserver.POJO;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/*
    LearnState

    The class is used for record a reviewing activity
 */

public class LearnState implements Serializable {
    private Integer score;
    private Float repeatChance;
    private Integer lastScore;
    private ArrayList<String> submittedAnswer;
    private ArrayList<String> lastAnswer;
    private ArrayList<String> answers;
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

    public Integer getLastScore() {
        return lastScore;
    }

    public void setLastScore(Integer lastScore) {
        this.lastScore = lastScore;
    }

    public ArrayList<String> getSubmittedAnswer() {
        return submittedAnswer;
    }

    public void setSubmittedAnswer(ArrayList<String> submittedAnswer) {
        this.submittedAnswer = submittedAnswer;
    }

    public ArrayList<String> getLastAnswer() {
        return lastAnswer;
    }

    public void setLastAnswer(ArrayList<String> lastAnswer) {
        this.lastAnswer = lastAnswer;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
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
                ", submittedAnswer=" + submittedAnswer +
                ", lastAnswer=" + lastAnswer +
                ", answers=" + answers +
                ", reviewedAt=" + reviewedAt +
                ", repeatedInterval=" + repeatedInterval +
                ", repeatAt=" + repeatAt +
                '}';
    }
}
