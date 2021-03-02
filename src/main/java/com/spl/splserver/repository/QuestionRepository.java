package com.spl.splserver.repository;

import com.spl.splserver.entity.Question;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends MongoRepository<Question, ObjectId>, CustomizedQuestionRepository {
    @Query("{ 'set_id' : ?0 }")
    List<Question> findBySetId(String setId);
}
