package com.projetofinal.ninjatask.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document(collection = "log")
public class LogEntity {
    @Id
    private String id;
    private int idUsuario;
    private String nomeUsuario;
    private String acao;
    private Date timestamp;
}
