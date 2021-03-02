package com.spl.splserver.repository;

import com.spl.splserver.entity.QuestionSet;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionSetRepository extends MongoRepository<QuestionSet, ObjectId> {
    @Query("{'owner_id' : ?0 }")
    List<QuestionSet> findByOwnerId(String ownerId);
}
