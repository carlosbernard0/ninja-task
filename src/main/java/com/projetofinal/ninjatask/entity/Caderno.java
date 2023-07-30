package com.projetofinal.ninjatask.entity;

import com.projetofinal.ninjatask.entity.Usuario;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Caderno {
    private Integer idCaderno;
    private String nomeCaderno;
//    private Integer idUsuario;
    private Usuario usuario;



}
