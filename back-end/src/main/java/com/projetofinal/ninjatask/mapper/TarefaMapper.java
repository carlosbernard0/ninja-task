package com.projetofinal.ninjatask.mapper;

import com.projetofinal.ninjatask.dto.TarefaDTO;
import com.projetofinal.ninjatask.entity.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TarefaMapper {

//    @Mapping(source = "idTarefaDto", target = "idTarefa")
//    @Mapping(source = "cadernoDto.caderno", target = "caderno.idCaderno")
    @Mapping(target = "usuario.dataRegistro", ignore = true)
    TarefaEntity toEntity(TarefaDTO dto);

//    @Mapping(source = "idTarefa", target = "idTarefaDto")
//    @Mapping(source = "caderno.idCaderno", target = "cadernoDto.caderno")
    @Mapping(target = "usuario.dataRegistro", ignore = true)
    TarefaDTO toDto(TarefaEntity entity);



}
