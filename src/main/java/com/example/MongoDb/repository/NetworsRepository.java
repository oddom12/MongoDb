package com.example.MongoDb.repository;


import com.example.MongoDb.model.Networks;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetworsRepository extends MongoRepository<Networks,String> {
}
