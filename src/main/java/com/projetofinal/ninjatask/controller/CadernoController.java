package com.projetofinal.ninjatask.controller;

import com.projetofinal.ninjatask.entity.Caderno;
import com.projetofinal.ninjatask.entity.Tarefa;
import com.projetofinal.ninjatask.repository.TarefaRepository;
import com.projetofinal.ninjatask.service.CadernoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/caderno")
public class CadernoController {
    @Autowired
    private CadernoService cadernoService;
    @PostMapping
    public Caderno inserirCaderno(@RequestBody Caderno caderno){
        return cadernoService.salvarCaderno(caderno);
    }

    @GetMapping
    public List<Caderno> retornarTodosOsCadernos() throws SQLException {
        return cadernoService.listar();
    }

    @DeleteMapping("/{id_caderno}")
    public boolean removerCaderno(@PathVariable("id_caderno") Integer id){
        return cadernoService.excluirCaderno(id);
    }
}
