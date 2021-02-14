package com.spl.splserver.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import java.util.Date;

/*
    QuestionSet Entity
 */
@Document("questions_set")
public class QuestionSet {
    @MongoId(value = FieldType.OBJECT_ID)
    private String id;
    @Field(name = "owner_Id")
    private String ownerId;
    private Integer score;
    private Integer quantity;
    @Field(name = "learn_rate")
    private Float learnRate;
    @CreatedDate
    @Field(name = "created_at")
    private Date createdAt;
    @LastModifiedDate
    @Field(name = "updated_at")
    private Date updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getLearnRate() {
        return learnRate;
    }

    public void setLearnRate(Float learnRate) {
        this.learnRate = learnRate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "QuestionSet{" +
                "id='" + id + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", score=" + score +
                ", quantity='" + quantity + '\'' +
                ", learnRate=" + learnRate +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
