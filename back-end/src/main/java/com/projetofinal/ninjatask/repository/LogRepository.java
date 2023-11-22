package com.projetofinal.ninjatask.repository;

import com.projetofinal.ninjatask.entity.LogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<LogEntity, String> {
}
