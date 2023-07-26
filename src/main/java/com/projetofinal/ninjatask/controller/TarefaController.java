package com.projetofinal.ninjatask.controller;


import com.projetofinal.ninjatask.entity.Tarefa;
import com.projetofinal.ninjatask.entity.Usuario;
import com.projetofinal.ninjatask.service.TarefaService;
import com.projetofinal.ninjatask.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private UsuarioController usuarioController;

    @Autowired
    private TarefaService tarefaService;
    @PostMapping
    public Tarefa inserirTarefa(@RequestBody Tarefa tarefa) throws Exception{
        return tarefaService.salvarTarefa(tarefa);
//        return tarefaSalva;
    }

    @GetMapping
    public List<Tarefa> retornarTodasAsTarefas() throws SQLException {
        return tarefaService.listarTarefas();
//        return lista;
    }

    @PutMapping
    public boolean atualizarTarefa(@RequestBody Tarefa tarefa) throws Exception {
        return tarefaService.editarTarefa(tarefa);
//        return tarefaEditada;
    }

    @DeleteMapping("/{id_tarefa}")
    public boolean removerTarefa(@PathVariable("id_tarefa") Integer id){
        return tarefaService.excluirTarefa(id);
//        return removido;
    }
}
