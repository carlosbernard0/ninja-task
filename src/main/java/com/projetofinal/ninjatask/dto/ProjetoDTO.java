package com.projetofinal.ninjatask.dto;

import com.projetofinal.ninjatask.entity.TipoProjeto;
import lombok.Data;

import java.util.Date;

@Data
public class ProjetoDTO {
    private String id;
    private String nomeUsuario; //entidade usuario
    private String nomeProjeto;
    private String descricao;
    private TipoProjeto tipoProjeto;
    private Date dataExpiracao;
}
