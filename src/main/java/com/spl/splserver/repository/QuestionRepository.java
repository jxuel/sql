package com.spl.splserver.repository;

import com.spl.splserver.entity.Question;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<Question, ObjectId>, CustomizedQuestionRepository {

}
