package com.projetofinal.ninjatask.controller;

import com.projetofinal.ninjatask.dto.LogDTO;
import com.projetofinal.ninjatask.entity.LogEntity;
import com.projetofinal.ninjatask.entity.UsuarioEntity;
import com.projetofinal.ninjatask.repository.LogRepository;
import com.projetofinal.ninjatask.repository.UsuarioRepository;
import com.projetofinal.ninjatask.service.LogService;
import com.projetofinal.ninjatask.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Controle de Logins")
@RequiredArgsConstructor
public class LogController {
    private final LogService logService;


    @GetMapping
    public List<LogDTO> retornarLog() throws SQLException {
        List<LogDTO> lista = logService.retornarLog();
        return lista;
    }
    @DeleteMapping
    public void deletarTodos(){
        logService.deletarTodos();
    }

}

