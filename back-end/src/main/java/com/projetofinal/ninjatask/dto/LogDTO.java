package com.projetofinal.ninjatask.dto;

import com.projetofinal.ninjatask.entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
public class LogDTO {
    private String id;
    private Integer idUsuario;
    private String nomeUsuario;
    private Date dataDeLogin;
}
