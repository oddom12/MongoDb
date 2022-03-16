package com.example.MongoDb.repository;

import com.example.MongoDb.model.SystemInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRepository extends MongoRepository<SystemInfo,String> {
}
