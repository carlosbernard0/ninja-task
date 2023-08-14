package com.projetofinal.ninjatask.mapper;

import com.projetofinal.ninjatask.dto.TarefaDto;
import com.projetofinal.ninjatask.entity.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TarefaMapper {

//    @Mapping(source = "idTarefaDto", target = "idTarefa")
//    @Mapping(source = "cadernoDto.caderno", target = "caderno.idCaderno")
    @Mapping(target = "usuario.dataRegistro", ignore = true)
    TarefaEntity toEntity(TarefaDto dto);

//    @Mapping(source = "idTarefa", target = "idTarefaDto")
//    @Mapping(source = "caderno.idCaderno", target = "cadernoDto.caderno")
    @Mapping(target = "usuario.dataRegistro", ignore = true)
    TarefaDto toDto(TarefaEntity entity);



}
