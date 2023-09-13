package com.projetofinal.ninjatask.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Data
public class UsuarioDTO {
    @Schema(description = "codigo indentificador do usuario", example = "1")
    private Integer idUsuario;

    @Schema(description = "nome do usuario", example = "travis scott")
    @NotEmpty
    @Size(min = 4, max = 20, message = "nome do usuario deve estar entre 5 e 20 caracteres")
    private String nomeUsuario;

    @Schema(description = "email do usuario", example = "carlos@gmail.com")
    private String emailUsuario;

    @Schema(description = "senha do usuario", example = "senha")
    @NotEmpty
    private String senhaUsuario;

    @Schema(description = "data de alteração do usuario", example = "2023-07-26")
    @PastOrPresent
    private Date dataRegistro;

    @Schema(description = "usuario ativo se for 1")
    private Boolean ativo;

}
