package com.projetofinal.ninjatask.repository;

import com.projetofinal.ninjatask.entity.CadernoEntity;
import com.projetofinal.ninjatask.entity.TarefaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TarefaRepository extends JpaRepository<TarefaEntity,Integer> {
//    List<TarefaEntity> findBycaderno(Integer caderno);

}
