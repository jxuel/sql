package com.spl.splserver.repository;

import com.spl.splserver.POJO.LearnState;
import com.spl.splserver.entity.Question;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class CustomizedQuestionRepositoryImpl implements CustomizedQuestionRepository{
    @Autowired
    private final MongoTemplate mongoTemplate;

    public CustomizedQuestionRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void updateLearnState(String questionId, LearnState state) {
        Query query = new Query().addCriteria(where("_id").is(new ObjectId(questionId)));
        Update update = new Update();
        update.set("reviewed_at", state.getReviewedAt());
        update.set("last_answer", state.getLastAnswer());
        update.set("last_score", state.getLastScore());
        update.set("repeat_at", state.getRepeatAt());
        update.set("repeated_interval", state.getRepeatedInterval());

        mongoTemplate.updateFirst(query,update, Question.class).getModifiedCount();
    }
}
