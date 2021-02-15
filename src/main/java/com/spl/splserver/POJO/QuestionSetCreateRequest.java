package com.spl.splserver.POJO;

import javax.validation.constraints.NotBlank;
/*
    QuestionSetCreateRequest

    The class is used on creating question set endpoint for check JSON body
 */
public class QuestionSetCreateRequest {
    @NotBlank(message = "ownerId can not be null")
    private String ownerId;
    @NotBlank(message = "name can not be empty")
    private String name;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "QuestionSetCreateRequest{" +
                "ownerId='" + ownerId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
