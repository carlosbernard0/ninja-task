package com.projetofinal.ninjatask.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Usuario {

    private Integer idUsuario;
    private String nomeUsuario;
    private String emailUsuario;
    private String senhaUsuario;
    private Date dataRegistro;

}
