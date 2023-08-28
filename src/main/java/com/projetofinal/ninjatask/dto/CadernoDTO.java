package com.projetofinal.ninjatask.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CadernoDTO {
    @Schema(description = "codigo indentificador do caderno", example = "1")
    private Integer idCaderno;

    @Schema(description = "nome do caderno", example = "caderno de tarefas")
    @NotEmpty
    @Size(min = 5, max = 20, message = "nome do caderno deve estar entre 5 e 20 caracteres")
    private String nomeCaderno;

    private UsuarioRetornoDTO usuario;
}