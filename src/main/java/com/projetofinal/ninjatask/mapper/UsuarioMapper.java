package com.projetofinal.ninjatask.mapper;

import com.projetofinal.ninjatask.dto.UsuarioDto;
import com.projetofinal.ninjatask.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    // converter dto em entity
    Usuario converterParaEntity(UsuarioDto dto);

    //converter entity em dto
    UsuarioDto converterParaDto(Usuario entity);
}
