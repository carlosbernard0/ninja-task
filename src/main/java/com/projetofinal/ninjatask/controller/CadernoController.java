package com.projetofinal.ninjatask.controller;

import com.projetofinal.ninjatask.dto.CadernoDto;
import com.projetofinal.ninjatask.entity.Caderno;
import com.projetofinal.ninjatask.entity.Tarefa;
import com.projetofinal.ninjatask.repository.TarefaRepository;
import com.projetofinal.ninjatask.service.CadernoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/caderno")
@RequiredArgsConstructor
public class CadernoController {
    private final CadernoService cadernoService;

    @Operation(summary = "inserir novo caderno", description = "este processo cria um novo caderno na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados"),
            @ApiResponse(responseCode = "500", description = "deu erro no servidor")
    })
    @PostMapping
    public CadernoDto inserirCaderno(@RequestBody CadernoDto caderno){
        return cadernoService.salvarCaderno(caderno);
    }

    @Operation(summary = "listar cadernos", description = "este processo mostra os cadernos da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados"),
            @ApiResponse(responseCode = "500", description = "deu erro no servidor")
    })
    @GetMapping
    public List<CadernoDto> retornarTodosOsCadernos() throws SQLException {
        return cadernoService.listar();
    }

    @Operation(summary = "excluir caderno", description = "este processo exclui o caderno na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados"),
            @ApiResponse(responseCode = "500", description = "deu erro no servidor")
    })
    @DeleteMapping("/{id_caderno}")
    public boolean removerCaderno(@PathVariable("id_caderno") Integer id){
        return cadernoService.excluirCaderno(id);
    }
}
