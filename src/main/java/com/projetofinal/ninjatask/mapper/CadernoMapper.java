package com.projetofinal.ninjatask.mapper;

import com.projetofinal.ninjatask.dto.CadernoDto;
import com.projetofinal.ninjatask.dto.UsuarioDto;
import com.projetofinal.ninjatask.entity.Caderno;
import com.projetofinal.ninjatask.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CadernoMapper {

//    @Mapping(source = "usuarioLista", target = "usuario")
//    @Mapping(target = "senhaUsuario", ignore = true)
//    @Mapping(target = "dataRegistro", ignore = true)
    Caderno converterParaEntity(CadernoDto dto);

    //converter entity em dto
//    @Mapping(source = "usuario", target = "usuarioLista")
    CadernoDto converterParaDto(Caderno entity);
}
