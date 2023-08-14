package com.projetofinal.ninjatask.service;

import com.projetofinal.ninjatask.dto.UsuarioDto;
import com.projetofinal.ninjatask.entity.UsuarioEntity;
import com.projetofinal.ninjatask.exceptions.BusinessException;
import com.projetofinal.ninjatask.mapper.UsuarioMapper;
import com.projetofinal.ninjatask.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;

    public void validarUsuario(UsuarioDto usuario) throws BusinessException {
        if (!usuario.getEmailUsuario().contains("@")){
            throw new BusinessException("Precisa ter @");
        }
    }
    public UsuarioDto salvarUsuario(UsuarioDto usuario) throws BusinessException{
        validarUsuario(usuario);
        //converter dto para entity
        UsuarioEntity usuarioEntityConvertido = usuarioMapper.toEntity(usuario);
        UsuarioEntity usuarioEntitySalvo = usuarioRepository.save(usuarioEntityConvertido);
        //converter entity para dto
        UsuarioDto usuarioRetornado = usuarioMapper.toDTO(usuarioEntitySalvo);
        return usuarioRetornado;

    }

    public List<UsuarioDto> listar() throws SQLException {
        List<UsuarioEntity> listaUsuario = usuarioRepository.findAll();
        List <UsuarioDto> dtos = listaUsuario.stream().map(entity -> usuarioMapper.toDTO(entity)).toList();
        return dtos;
    }



    public void excluirUsuario(Integer idUsuario){
        usuarioRepository.deleteById(idUsuario);
    }
}
