package com.dionisius.finalproject.logservice.repository;

import com.dionisius.finalproject.logservice.model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends MongoRepository<Log,String> {
}
