package com.apandit.journalApp.repository;

import com.apandit.journalApp.entity.ConfigJournalAppEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {



}
