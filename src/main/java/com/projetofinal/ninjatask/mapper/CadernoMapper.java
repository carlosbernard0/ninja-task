package com.projetofinal.ninjatask.mapper;

import com.projetofinal.ninjatask.dto.CadernoDto;
import com.projetofinal.ninjatask.dto.UsuarioDto;
import com.projetofinal.ninjatask.entity.Caderno;
import com.projetofinal.ninjatask.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CadernoMapper {
    Caderno converterParaEntity(CadernoDto dto);

    //converter entity em dto
    CadernoDto converterParaDto(Caderno entity);
}
