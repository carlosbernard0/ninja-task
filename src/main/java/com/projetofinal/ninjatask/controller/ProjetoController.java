package com.projetofinal.ninjatask.controller;

import com.projetofinal.ninjatask.dto.ProjetoDTO;
import com.projetofinal.ninjatask.entity.ProjetoEntity;
import com.projetofinal.ninjatask.service.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projeto")
@RequiredArgsConstructor
public class ProjetoController {
    private final ProjetoService projetoService;

    @PostMapping
    public ProjetoDTO criarProjeto(@RequestBody ProjetoDTO projeto){
        return projetoService.criarProjeto(projeto);
    }

    @GetMapping
    public List<ProjetoDTO> listarProjetos(){
        return projetoService.listar();
    }
}
