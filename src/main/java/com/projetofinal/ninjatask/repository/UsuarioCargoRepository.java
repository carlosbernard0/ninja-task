package com.projetofinal.ninjatask.repository;

import com.projetofinal.ninjatask.entity.CargoEntity;
import com.projetofinal.ninjatask.entity.UsuarioCargoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioCargoRepository extends JpaRepository<UsuarioCargoEntity, Integer> {
}
