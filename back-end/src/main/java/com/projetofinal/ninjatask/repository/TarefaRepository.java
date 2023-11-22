package com.projetofinal.ninjatask.repository;

import com.projetofinal.ninjatask.entity.TarefaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<TarefaEntity,Integer> {
//    List<TarefaEntity> findBycaderno(Integer caderno);

}
