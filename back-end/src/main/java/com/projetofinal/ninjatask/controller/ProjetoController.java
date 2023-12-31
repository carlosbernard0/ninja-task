package com.projetofinal.ninjatask.controller;

import com.projetofinal.ninjatask.dto.ProjetoDTO;
import com.projetofinal.ninjatask.dto.ProjetosPorTipoDTO;
import com.projetofinal.ninjatask.entity.ProjetoEntity;
import com.projetofinal.ninjatask.entity.ProjetosPorTipoEntity;
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
    public List<ProjetoDTO> listarProjetos(String nomeUsuario){
        return projetoService.listar(nomeUsuario);
    }

    @DeleteMapping
    public void delete(String id){
        projetoService.excluir(id);
    }

    @PutMapping
    public ProjetoDTO editarProjeto(@RequestBody ProjetoDTO projetoDTO){
        return projetoService.criarProjeto(projetoDTO);
    }

    @GetMapping("/listarPorTipoAgregado")
    public List<ProjetosPorTipoDTO> listarPorTipoAgregado(){
        return projetoService.listarPorTipoAgregado();
    }
    
    
}
