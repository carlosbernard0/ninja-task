package com.projetofinal.ninjatask.entity.security;

import com.projetofinal.ninjatask.entity.UsuarioEntity;
import com.projetofinal.ninjatask.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
 
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final UsuarioService usuarioService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioEntity> usuarioEntityOptional = usuarioService.findByEmailUsuario(username);
        if (usuarioEntityOptional.isPresent()){
            return usuarioEntityOptional.get();
        }
        throw new UsernameNotFoundException("Usuario não encontrado");
    }


}
