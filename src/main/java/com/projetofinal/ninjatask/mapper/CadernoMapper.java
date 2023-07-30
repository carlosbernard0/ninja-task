package com.projetofinal.ninjatask.mapper;

import com.projetofinal.ninjatask.dto.CadernoDto;
import com.projetofinal.ninjatask.dto.UsuarioDto;
import com.projetofinal.ninjatask.entity.Caderno;
import com.projetofinal.ninjatask.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CadernoMapper {

    @Mapping(source = "caderno", target = "idCaderno")
//    @Mapping(source = "usuarioLista", target = "usuario")
    @Mapping(source = "usuarioId.usuarioDto", target = "usuario.idUsuario")
    Caderno converterParaEntity(CadernoDto dto);

    //converter entity em dto
    @Mapping(source = "idCaderno", target = "caderno")
//    @Mapping(source = "usuario", target = "usuarioLista")
    @Mapping(source = "usuario.idUsuario", target = "usuarioId.usuarioDto")
    CadernoDto converterParaDto(Caderno entity);
}
