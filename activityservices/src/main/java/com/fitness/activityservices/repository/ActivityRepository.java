package com.fitness.activityservices.repository;

import com.fitness.activityservices.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityRepository extends MongoRepository<Activity,String> {
}
