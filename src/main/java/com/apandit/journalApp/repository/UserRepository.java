package com.apandit.journalApp.repository;

import com.apandit.journalApp.entity.JournalEntry;
import com.apandit.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);
}
