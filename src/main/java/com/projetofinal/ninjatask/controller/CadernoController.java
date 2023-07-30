package com.projetofinal.ninjatask.controller;

import com.projetofinal.ninjatask.dto.CadernoDto;
import com.projetofinal.ninjatask.entity.Caderno;
import com.projetofinal.ninjatask.entity.Tarefa;
import com.projetofinal.ninjatask.repository.TarefaRepository;
import com.projetofinal.ninjatask.service.CadernoService;
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

    @PostMapping
    public CadernoDto inserirCaderno(@RequestBody CadernoDto caderno){
        return cadernoService.salvarCaderno(caderno);
    }

    @GetMapping
    public List<CadernoDto> retornarTodosOsCadernos() throws SQLException {
        return cadernoService.listar();
    }

    @DeleteMapping("/{id_caderno}")
    public boolean removerCaderno(@PathVariable("id_caderno") Integer id){
        return cadernoService.excluirCaderno(id);
    }
}
