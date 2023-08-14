package com.projetofinal.ninjatask.repository;

import com.projetofinal.ninjatask.entity.CadernoEntity;
import com.projetofinal.ninjatask.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Integer> {
}
