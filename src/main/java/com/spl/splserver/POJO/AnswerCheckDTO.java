package com.spl.splserver.POJO;

import java.util.List;

public class AnswerCheckDTO {
    private String questionId;
    private List<String> answers;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "AnswerCheckDTO{" +
                "questionId='" + questionId + '\'' +
                ", answers=" + answers +
                '}';
    }
}
