package com.projetofinal.ninjatask.repository;

import com.projetofinal.ninjatask.entity.CargoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioCargoRepository extends JpaRepository<CargoEntity, Integer> {
}
