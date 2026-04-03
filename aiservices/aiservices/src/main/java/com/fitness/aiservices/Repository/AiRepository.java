package com.fitness.aiservices.Repository;

import com.fitness.aiservices.model.Recommendation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AiRepository extends MongoRepository<Recommendation,String>{
}
