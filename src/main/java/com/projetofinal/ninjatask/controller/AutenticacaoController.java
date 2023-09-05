package com.projetofinal.ninjatask.controller;

import com.projetofinal.ninjatask.dto.AutenticacaoDTO;
import com.projetofinal.ninjatask.dto.UsuarioDTO;
import com.projetofinal.ninjatask.entity.UsuarioEntity;
import com.projetofinal.ninjatask.exceptions.BusinessException;
import com.projetofinal.ninjatask.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autenticacao")
@RequiredArgsConstructor
public class AutenticacaoController {
    private final UsuarioService usuarioService;
    @PostMapping("/login")
    public String fazerLogin(@RequestBody AutenticacaoDTO autenticacaoDTO) throws BusinessException {
        return usuarioService.fazerLogin(autenticacaoDTO);
    }

    @GetMapping("/usuario-logado")
    public UsuarioEntity recuperarUsuarioLogado() throws BusinessException {
        return usuarioService.recuperarUsuarioLogado();
    }
}
