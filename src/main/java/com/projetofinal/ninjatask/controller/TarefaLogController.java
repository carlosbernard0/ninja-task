package com.projetofinal.ninjatask.controller;

import com.projetofinal.ninjatask.dto.LogDTO;
import com.projetofinal.ninjatask.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/controle-de-tarefas")
@RequiredArgsConstructor
public class TarefaLogController {
    private final LogService logService;

    @GetMapping
    public List<LogDTO> retornarLogTarefas() throws SQLException {
        List<LogDTO> lista = logService.retornarLog();
        return lista;
    }
}

