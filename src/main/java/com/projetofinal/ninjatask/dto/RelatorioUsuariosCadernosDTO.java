package com.projetofinal.ninjatask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioUsuariosCadernosDTO {
    private Integer idUsuario;
    private String nomeUsuario;
    private Integer idCaderno;
    private String nomeCaderno;
    private Integer idTarefa;
    private String nome;
}
