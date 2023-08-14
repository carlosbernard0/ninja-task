package com.projetofinal.ninjatask.mapper;

import com.projetofinal.ninjatask.dto.UsuarioDto;
import com.projetofinal.ninjatask.entity.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    // converter dto em entity

//    @Mapping(target = "senhaUsuario", ignore = true)
//    @Mapping(target = "dataRegistro", ignore = true)
    UsuarioEntity toEntity(UsuarioDto dto);

    //converter entity em dto
//    @Mapping(target = "dataRegistro", ignore = true)
//    @Mapping(target = "senhaUsuario", ignore = true)
    UsuarioDto toDTO(UsuarioEntity entity);
}
