package com.example.MongoDb.repository;


import com.example.MongoDb.model.Processes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessesRepository extends MongoRepository<Processes,String> {


}
