package com.projetofinal.ninjatask.repository;

import com.projetofinal.ninjatask.dto.RelatorioUsuariosCadernosDTO;
import com.projetofinal.ninjatask.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity ,Integer> {

    @Query("    Select new com.projetofinal.ninjatask.dto.RelatorioUsuariosCadernosDTO(u.idUsuario," +
            "           u.nomeUsuario," +
            "           c.idCaderno," +
            "           c.nomeCaderno," +
            "           t.idTarefa," +
            "           t.nome)" +
            "   from Usuario u" +
            "   inner join u.cadernos c" +
            "   inner join c.tarefas t")
    List<RelatorioUsuariosCadernosDTO> buscarUsuariosCadernosETarefas();
}
