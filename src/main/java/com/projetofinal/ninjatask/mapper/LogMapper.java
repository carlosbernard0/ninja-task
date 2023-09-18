package com.projetofinal.ninjatask.mapper;

import com.projetofinal.ninjatask.dto.CadernoDTO;
import com.projetofinal.ninjatask.dto.LogDTO;
import com.projetofinal.ninjatask.dto.TarefaDTO;
import com.projetofinal.ninjatask.entity.CadernoEntity;
import com.projetofinal.ninjatask.entity.LogEntity;
import com.projetofinal.ninjatask.entity.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LogMapper {
    LogEntity toEntity(LogDTO dto);

    LogDTO toDto(LogEntity entity);
}
