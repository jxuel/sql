package com.spl.splserver.repository;

import com.spl.splserver.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findUserByUsernameAndPassword(String username, String password);
    User findUserByUsername(String username);

}
