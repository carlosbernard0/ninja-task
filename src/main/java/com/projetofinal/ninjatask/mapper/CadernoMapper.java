package com.projetofinal.ninjatask.mapper;

import com.projetofinal.ninjatask.dto.CadernoDto;
import com.projetofinal.ninjatask.entity.CadernoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CadernoMapper {

//    @Mapping(source = "usuarioLista", target = "usuario")
//    @Mapping(target = "senhaUsuario", ignore = true)
//    @Mapping(target = "dataRegistro", ignore = true)
//@Mapping(target = "dataRegistro", ignore = true)
    CadernoEntity converterParaEntity(CadernoDto dto);

    //converter entity em dto
//    @Mapping(source = "usuario", target = "usuarioLista")
//    @Mapping(target = "dataRegistro", ignore = true)
    CadernoDto converterParaDto(CadernoEntity entity);
}
