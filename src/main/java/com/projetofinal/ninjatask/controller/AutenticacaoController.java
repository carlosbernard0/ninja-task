package com.projetofinal.ninjatask.controller;

import com.projetofinal.ninjatask.dto.AutenticacaoDTO;
import com.projetofinal.ninjatask.exceptions.BusinessException;
import com.projetofinal.ninjatask.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
@RequiredArgsConstructor
public class AutenticacaoController {
    private final UsuarioService usuarioService;
    @PostMapping("/login")
    public String fazerLogin(@RequestBody AutenticacaoDTO autenticacaoDTO) throws BusinessException {
        return usuarioService.fazerLogin(autenticacaoDTO);
    }
}
