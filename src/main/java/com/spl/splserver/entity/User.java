package com.spl.splserver.entity;

import com.spl.splserver.POJO.UserRole;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
import java.util.HashMap;

/*
    User

    Store user data
 */
@Document("l2w_user")
public class User {

    @MongoId(value = FieldType.OBJECT_ID)
    private String id;
    private String username;
    private String password;
    private String userId;
    private HashMap<String, String> profile;
    private UserRole role;
    @Field(name = "access_token")
    private String accessToken;
    @CreatedDate
    @Field(name = "created_at")
    private Date createdAt;
    @LastModifiedDate
    @Field(name = "updated_at")
    private Date updatedAt;
    @Field(name = "service_end_at")
    private Date serviceEndAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public HashMap<String, String> getProfile() {
        return profile;
    }

    public void setProfile(HashMap<String, String> profile) {
        this.profile = profile;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
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

    public Date getServiceEndAt() {
        return serviceEndAt;
    }

    public void setServiceEndAt(Date serviceEndAt) {
        this.serviceEndAt = serviceEndAt;
    }
}
