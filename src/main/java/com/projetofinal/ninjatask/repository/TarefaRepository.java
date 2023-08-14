package com.projetofinal.ninjatask.repository;

import com.projetofinal.ninjatask.entity.CadernoEntity;
import com.projetofinal.ninjatask.entity.TarefaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<TarefaEntity,Integer> {
}
