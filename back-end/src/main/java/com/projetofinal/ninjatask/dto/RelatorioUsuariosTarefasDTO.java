package com.projetofinal.ninjatask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioUsuariosTarefasDTO {
    private Integer idUsuario;
    private String nomeUsuario;
    private Integer idTarefa;
    private String nome;
}
