package com.projetofinal.ninjatask.service;

import com.projetofinal.ninjatask.dto.UsuarioDto;
import com.projetofinal.ninjatask.entity.Usuario;
import com.projetofinal.ninjatask.exceptions.BusinessException;
import com.projetofinal.ninjatask.mapper.UsuarioMapper;
import com.projetofinal.ninjatask.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        Usuario usuarioConvertido = usuarioMapper.converterParaEntity(usuario);

        Usuario usuarioSalvo = usuarioRepository.cadastrarUsuario(usuarioConvertido);

        //converter entity para dto
        UsuarioDto usuarioRetornado = usuarioMapper.converterParaDto(usuarioSalvo);
        return usuarioRetornado;

    }

    public List<UsuarioDto> listar() throws SQLException {
        List<UsuarioDto> listaDeDtos = this.usuarioRepository.listarUsuario().stream()
                .map(entity -> usuarioMapper.converterParaDto(entity))
                .toList();
        return listaDeDtos;
    }

    public boolean editarUsuario(UsuarioDto usuarioDto) throws BusinessException {
        validarUsuario(usuarioDto);
        Usuario usuarioConvertido = usuarioMapper.converterParaEntity(usuarioDto);


        return usuarioRepository.editarUsuario(usuarioConvertido);
    }



    public boolean excluirUsuario(Integer idUsuario){
        return this.usuarioRepository.excluirUsuario(idUsuario);
    }
}
