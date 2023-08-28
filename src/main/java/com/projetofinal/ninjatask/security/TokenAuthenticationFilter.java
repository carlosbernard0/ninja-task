package com.projetofinal.ninjatask.security;

import com.projetofinal.ninjatask.controller.UsuarioController;
import com.projetofinal.ninjatask.entity.UsuarioEntity;
import com.projetofinal.ninjatask.exceptions.BusinessException;
import com.projetofinal.ninjatask.service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final UsuarioService usuarioService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        //primeiro verifica o Token
        String tokenBearer = request.getHeader("Authorization");
        try {
            UsuarioEntity usuario = usuarioService.validarToken(tokenBearer);
            UsernamePasswordAuthenticationToken tokenSpring = new UsernamePasswordAuthenticationToken(usuario, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(tokenSpring);
        } catch (BusinessException e) {
            SecurityContextHolder.getContext().setAuthentication(null); // não está Logado
        }
        //Depois valida
        filterChain.doFilter(request, response);
    }
}
