package com.example.spring_demo_app.repository;

import com.example.spring_demo_app.domain.entity.AccountEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<AccountEntity,String> {
}
