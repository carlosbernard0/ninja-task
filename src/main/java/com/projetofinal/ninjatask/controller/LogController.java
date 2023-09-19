package com.projetofinal.ninjatask.controller;

import com.projetofinal.ninjatask.dto.LogDTO;
import com.projetofinal.ninjatask.entity.LogEntity;
import com.projetofinal.ninjatask.entity.UsuarioEntity;
import com.projetofinal.ninjatask.repository.LogRepository;
import com.projetofinal.ninjatask.repository.UsuarioRepository;
import com.projetofinal.ninjatask.service.LogService;
import com.projetofinal.ninjatask.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Controle de Logins")
@RequiredArgsConstructor
public class LogController {
    private final LogService logService;


    @GetMapping
    public List<LogDTO> retornarHistorico() throws SQLException {
        List<LogDTO> lista = logService.historico();
        return lista;
    }

}

