package com.example.twitter.repository;

import com.example.twitter.model.Thread;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ThreadRepository extends MongoRepository<Thread, String> {
}
