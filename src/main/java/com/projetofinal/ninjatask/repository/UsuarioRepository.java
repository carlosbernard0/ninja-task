package com.projetofinal.ninjatask.repository;

import com.projetofinal.ninjatask.dto.RelatorioUsuariosCadernosDTO;
import com.projetofinal.ninjatask.entity.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity ,Integer> {
    Optional<UsuarioEntity> findByEmailUsuarioAndSenhaUsuario(String emailUsuario, String senhaUsuario);
    Optional<UsuarioEntity> findByEmailUsuario(String emailUsuario);
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

//    Page<UsuarioEntity> findAllByAtivoTrue(Pageable paginacao);

}
