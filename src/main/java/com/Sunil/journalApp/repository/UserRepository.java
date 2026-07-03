package com.Sunil.journalApp.repository;

import com.Sunil.journalApp.entity.JournalEntry;
import com.Sunil.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    public User findByUsername(String username);
}
