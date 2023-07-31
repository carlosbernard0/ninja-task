package com.projetofinal.ninjatask.mapper;

import com.projetofinal.ninjatask.dto.CadernoDto;
import com.projetofinal.ninjatask.dto.TarefaDto;
import com.projetofinal.ninjatask.entity.Caderno;
import com.projetofinal.ninjatask.entity.Tarefa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TarefaMapper {

//    @Mapping(source = "idTarefaDto", target = "idTarefa")
//    @Mapping(source = "cadernoDto.caderno", target = "caderno.idCaderno")
    Tarefa converterParaEntity(TarefaDto dto);

//    @Mapping(source = "idTarefa", target = "idTarefaDto")
//    @Mapping(source = "caderno.idCaderno", target = "cadernoDto.caderno")
    TarefaDto converterParaDto(Tarefa entity);



}
