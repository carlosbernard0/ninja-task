package com.projetofinal.ninjatask.mapper;

import com.projetofinal.ninjatask.dto.ProjetosPorTipoDTO;
import com.projetofinal.ninjatask.entity.ProjetosPorTipoEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ProjetosPorTipoMapper {
    ProjetosPorTipoEntity toEntity(ProjetosPorTipoDTO dto);

    ProjetosPorTipoDTO toDTO(ProjetosPorTipoEntity entity);
}
