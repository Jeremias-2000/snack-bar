package com.test.snack.bar.repository;

import com.test.snack.bar.model.Snack;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnackRepository extends MongoRepository<Snack,String> {
}
