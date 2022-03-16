package com.example.MongoDb.repository;

import com.example.MongoDb.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends MongoRepository<Users,String> {

}
