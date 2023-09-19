package com.projetofinal.ninjatask.repository;

import com.projetofinal.ninjatask.entity.ProjetoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends MongoRepository<ProjetoEntity, String> {
}
