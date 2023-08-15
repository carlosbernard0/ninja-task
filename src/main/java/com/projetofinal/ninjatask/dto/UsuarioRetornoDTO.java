package com.projetofinal.ninjatask.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioRetornoDTO {
    @Schema(description = "codigo indentificador do usuario", example = "4")
    private Integer idUsuario;

    @Schema(description = "nome do usuario", example = "travis scott")
    @NotEmpty
    @Size(min = 5, max = 20, message = "nome do usuario deve estar entre 5 e 20 caracteres")
    private String nomeUsuario;

    @Schema(description = "email do usuario", example = "carlos@gmail.com")
    private String emailUsuario;


}
