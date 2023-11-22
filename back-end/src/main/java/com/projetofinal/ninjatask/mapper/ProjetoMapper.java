package com.projetofinal.ninjatask.mapper;

import com.projetofinal.ninjatask.dto.ProjetoDTO;
import com.projetofinal.ninjatask.entity.ProjetoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjetoMapper {
    ProjetoEntity toEntity(ProjetoDTO dto);
    ProjetoDTO toDTO(ProjetoEntity entity);
}
