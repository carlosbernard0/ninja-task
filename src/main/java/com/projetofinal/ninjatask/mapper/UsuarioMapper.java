package com.projetofinal.ninjatask.mapper;

import com.projetofinal.ninjatask.dto.UsuarioDto;
import com.projetofinal.ninjatask.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    // converter dto em entity

//    @Mapping(target = "senhaUsuario", ignore = true)
//    @Mapping(target = "dataRegistro", ignore = true)
    Usuario converterParaEntity(UsuarioDto dto);

    //converter entity em dto
//    @Mapping(target = "dataRegistro", ignore = true)
//    @Mapping(target = "senhaUsuario", ignore = true)
    UsuarioDto converterParaDto(Usuario entity);
}
