package com.projetofinal.ninjatask.controller;

import com.projetofinal.ninjatask.dto.CadernoDTO;
import com.projetofinal.ninjatask.service.CadernoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
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
    public CadernoDTO inserirCaderno(@RequestBody CadernoDTO caderno){
        return cadernoService.salvarCaderno(caderno);
    }

    @Operation(summary = "listar cadernos", description = "este processo mostra os cadernos da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados"),
            @ApiResponse(responseCode = "500", description = "deu erro no servidor")
    })
    @GetMapping
    public List<CadernoDTO> listarCadernos() throws SQLException {
        return cadernoService.listar();
    }

    @Operation(summary = "excluir caderno", description = "este processo exclui o caderno na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados"),
            @ApiResponse(responseCode = "500", description = "deu erro no servidor")
    })
    @DeleteMapping("/{id_caderno}")
    public void removerCaderno(@PathVariable("id_caderno") Integer id){
        cadernoService.excluirCaderno(id);
    }
}
