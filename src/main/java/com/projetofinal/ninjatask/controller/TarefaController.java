package com.projetofinal.ninjatask.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.projetofinal.ninjatask.dto.PaginaDTO;
import com.projetofinal.ninjatask.dto.TarefaDTO;
import com.projetofinal.ninjatask.entity.TarefaEntity;
import com.projetofinal.ninjatask.entity.UsuarioEntity;
import com.projetofinal.ninjatask.exceptions.BusinessException;
import com.projetofinal.ninjatask.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/tarefa")
@RequiredArgsConstructor
@Validated

public class TarefaController {
    private final TarefaService tarefaService;

    @Operation(summary = "inserir nova tarefa", description = "este processo cria uma nova tarefa na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados"),
            @ApiResponse(responseCode = "500", description = "deu erro no servidor")
    })
    @PostMapping
    public TarefaDTO inserirTarefa(@RequestBody TarefaDTO dto) throws Exception{
        return tarefaService.salvarTarefa(dto);
//        return tarefaSalva;
    }

    //listar tarefas
    @GetMapping
    public List<TarefaDTO> listarTarefas() throws SQLException {
        return tarefaService.listarTarefas();
//        return lista;
    }

    @GetMapping("/listar-por-id")
    public List<TarefaDTO> listarTarefasPorId(Integer id) throws SQLException, JsonProcessingException {
        return tarefaService.listarPorId(id);
    }

    @Operation(summary = "editar tarefa", description = "este processo edita a tarefa da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados"),
            @ApiResponse(responseCode = "500", description = "deu erro no servidor")
    })
    @PutMapping
    public TarefaDTO atualizarTarefa(@RequestBody TarefaDTO dto) throws Exception {
        return tarefaService.editarTarefa(dto);
    }

    @Operation(summary = "excluir tarefa", description = "este processo exclui a tarefa da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados"),
            @ApiResponse(responseCode = "500", description = "deu erro no servidor")
    })
    @DeleteMapping("/{id_tarefa}")
    public void removerTarefa(@PathVariable("id_tarefa") Integer id) throws BusinessException, JsonProcessingException {
        tarefaService.excluirTarefa(id);
    }

    @GetMapping("/listarPaginado")
    public PaginaDTO<TarefaDTO> listarPaginado(Integer paginaSolicitada, Integer tamanhoPorPagina){
        return tarefaService.listarPaginado(paginaSolicitada, tamanhoPorPagina);
    }


}
