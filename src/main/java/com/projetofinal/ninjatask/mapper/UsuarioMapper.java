package com.projetofinal.ninjatask.mapper;

import com.projetofinal.ninjatask.dto.UsuarioDTO;
import com.projetofinal.ninjatask.entity.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    // converter dto em entity

//    @Mapping(target = "senhaUsuario", ignore = true)
//    @Mapping(target = "dataRegistro", ignore = true)
    UsuarioEntity toEntity(UsuarioDTO dto);

    //converter entity em dto
//    @Mapping(target = "dataRegistro", ignore = true)
//    @Mapping(target = "senhaUsuario", ignore = true)
    UsuarioDTO toDTO(UsuarioEntity entity);
}
