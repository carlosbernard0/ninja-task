package com.projetofinal.ninjatask.controller;

import com.projetofinal.ninjatask.dto.LogDTO;
import com.projetofinal.ninjatask.entity.LogEntity;
import com.projetofinal.ninjatask.repository.LogRepository;
import com.projetofinal.ninjatask.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/historico")
@RequiredArgsConstructor
public class LogController {
    private final LogRepository logRepository;
    private final LogService logService;

    @GetMapping
    public List<LogDTO> retornarHistorico() throws SQLException {
        List<LogDTO> lista = logService.historico();
        return lista;
    }
}
