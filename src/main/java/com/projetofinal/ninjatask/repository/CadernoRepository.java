package com.projetofinal.ninjatask.repository;

import com.projetofinal.ninjatask.entity.CadernoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CadernoRepository extends JpaRepository<CadernoEntity,Integer> {


}
