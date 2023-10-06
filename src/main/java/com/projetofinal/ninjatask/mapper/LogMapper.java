package com.projetofinal.ninjatask.mapper;

import com.projetofinal.ninjatask.dto.LogDTO;
import com.projetofinal.ninjatask.entity.LogEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LogMapper {
    LogEntity toEntity(LogDTO dto);

    LogDTO toDto(LogEntity entity);
}
