package com.projetofinal.ninjatask.controller;


import com.projetofinal.ninjatask.dto.TarefaDto;
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
    public TarefaDto inserirTarefa(@RequestBody TarefaDto dto) throws Exception{
        return tarefaService.salvarTarefa(dto);
//        return tarefaSalva;
    }

    //listar tarefas
    @GetMapping
    public List<TarefaDto> listarTarefas() throws SQLException {
        return tarefaService.listarTarefas();
//        return lista;
    }

//    @Operation(summary = "listar tarefas por caderno", description = "este processo mostra as tarefas que esta no caderno selecionado na base de dados")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Deu certo"),
//            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados"),
//            @ApiResponse(responseCode = "500", description = "deu erro no servidor")
//    })
//    @GetMapping("listar-tarefas-por-caderno")
//    public List<TarefaDto> retornarTarefasPorCaderno(@RequestParam("caderno") Integer caderno) throws SQLException {
//        return (List<TarefaDto>) tarefaService.listarPorCadernoDto(caderno);
//    }

    @Operation(summary = "editar tarefa", description = "este processo edita a tarefa da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados"),
            @ApiResponse(responseCode = "500", description = "deu erro no servidor")
    })
    @PutMapping
    public TarefaDto atualizarTarefa(@RequestBody TarefaDto dto) throws Exception {
        return tarefaService.salvarTarefa(dto);
    }

    @Operation(summary = "excluir tarefa", description = "este processo exclui a tarefa da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados"),
            @ApiResponse(responseCode = "500", description = "deu erro no servidor")
    })
    @DeleteMapping("/{id_tarefa}")
    public void removerTarefa(@PathVariable("id_tarefa") Integer id){
        tarefaService.excluirTarefa(id);
    }
}
