package com.spl.splserver.entity;

import com.mongodb.lang.NonNull;
import com.spl.splserver.POJO.QuestionPriority;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
/*
    Question

    Store Question information


 */

import java.util.ArrayList;
import java.util.Date;

@Document("questions")
public class Question {
    @MongoId(value = FieldType.OBJECT_ID)
    private String id;
    @Field(name = "set_id")
    private String setId;
    @Field(name = "owner_id")
    private String ownerId;
    private String question;
    private ArrayList<String> options;
    private ArrayList<String> answers;
    @Field(name = "last_answer")
    private ArrayList<String> lastAnswer;
    private QuestionPriority priority;
    @Field("repeat_chance")
    private Float repeatChance;
    @Field(name = "last_score")
    private Integer lastScore;
    @Field(name = "reviewed_at")
    private Date reviewedAt;
    @Field(name = "repeated_interval")
    private Long repeatInterval;
    @Field(name = "repeat_at")
    private Date repeatAt;
    @CreatedDate
    @Field(name = "created_at")
    private String createdAt;
    @LastModifiedDate
    @Field(name = "updated_at")
    private String updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    @NonNull
    public String getSetId() {
        return setId;
    }

    public void setSetId(@NonNull String setId) {
        this.setId = setId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public ArrayList<String> getLastAnswer() {
        return lastAnswer;
    }

    public void setLastAnswer(ArrayList<String> lastAnswer) {
        this.lastAnswer = lastAnswer;
    }

    public QuestionPriority getPriority() {
        return priority;
    }

    public void setPriority(QuestionPriority priority) {
        this.priority = priority;
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

    public Date getReviewedAt() {
        return reviewedAt;
    }

    public void setReviewedAt(Date reviewedAt) {
        this.reviewedAt = reviewedAt;
    }

    public Long getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(Long repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public Date getRepeatAt() {
        return repeatAt;
    }

    public void setRepeatAt(Date repeatAt) {
        this.repeatAt = repeatAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Question{" +
                "Id='" + id + '\'' +
                ", setId='" + setId + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", question='" + question + '\'' +
                ", options=" + options +
                ", answers=" + answers +
                ", lastAnswer=" + lastAnswer +
                ", priority=" + priority +
                ", repeatChance=" + repeatChance +
                ", lastScore=" + lastScore +
                ", reviewedAt=" + reviewedAt +
                ", repeatInterval=" + repeatInterval +
                ", repeatAt=" + repeatAt +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
