package com.some_service.dimitrii.repositories;

import com.some_service.dimitrii.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity,String> {
}
