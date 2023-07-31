package com.projetofinal.ninjatask.controller;


import com.projetofinal.ninjatask.dto.TarefaDto;
import com.projetofinal.ninjatask.entity.Tarefa;
import com.projetofinal.ninjatask.entity.Usuario;
import com.projetofinal.ninjatask.service.TarefaService;
import com.projetofinal.ninjatask.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/tarefa")
@RequiredArgsConstructor
public class TarefaController {
    private  final CadernoController cadernoController;
    private final TarefaService tarefaService;

    @PostMapping
    public TarefaDto inserirTarefa(@RequestBody TarefaDto tarefaDto) throws Exception{
        return tarefaService.salvarTarefa(tarefaDto);
//        return tarefaSalva;
    }

    @GetMapping
    public List<TarefaDto> retornarTodasAsTarefas() throws SQLException {
        return tarefaService.listarTarefas();
//        return lista;
    }

    @PutMapping
    public boolean atualizarTarefa(@RequestBody TarefaDto tarefaDto) throws Exception {
        return tarefaService.editarTarefa(tarefaDto);
//        return tarefaEditada;
    }

    @DeleteMapping("/{id_tarefa}")
    public boolean removerTarefa(@PathVariable("id_tarefa") Integer id){
        return tarefaService.excluirTarefa(id);
//        return removido;
    }
}
