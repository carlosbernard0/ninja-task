package com.projetofinal.ninjatask.repository;

import com.projetofinal.ninjatask.dto.RelatorioUsuariosTarefasDTO;
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

    Optional<UsuarioEntity> findByIdUsuario(Integer idLogado);

    @Query("    Select new com.projetofinal.ninjatask.dto.RelatorioUsuariosTarefasDTO(u.idUsuario," +
            "           u.nomeUsuario," +
            "           t.idTarefa," +
            "           t.nome)" +
            "   from Usuario u" +
            "   inner join Tarefa t")
    List<RelatorioUsuariosTarefasDTO> buscarUsuariosETarefas();

//    Page<UsuarioEntity> findAllByAtivoTrue(Pageable paginacao);

}
