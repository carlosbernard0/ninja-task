package com.projetofinal.ninjatask.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjetosPorTipoEntity {
    @Id
    private String tipo;
    private Integer quantidade;
}
