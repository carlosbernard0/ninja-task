package com.projetofinal.ninjatask.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document(collection = "projeto")
public class ProjetoEntity {
    @Id
    private String id;
    private String nomeUsuario;
    private String nomeProjeto;
    private String descricao;
    private TipoProjeto tipoProjeto;
    private Date dataExpiracao;
}
