package com.spl.splserver.repository;

import com.spl.splserver.entity.QuestionSet;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionSetRepository extends MongoRepository<QuestionSet, ObjectId> {
}
