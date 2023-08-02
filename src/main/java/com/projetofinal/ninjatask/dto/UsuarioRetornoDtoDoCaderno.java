package com.projetofinal.ninjatask.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioRetornoDtoDoCaderno {
    @Schema(description = "codigo indentificador do usuario", example = "4")
    private Integer idUsuario;



}
