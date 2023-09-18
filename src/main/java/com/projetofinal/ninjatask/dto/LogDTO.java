package com.projetofinal.ninjatask.dto;

import com.projetofinal.ninjatask.entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class LogDTO {
    private String id;
    private int idUsuario;
    private String nomeUsuario;
    private String acao;
    private Date timestamp;
}
