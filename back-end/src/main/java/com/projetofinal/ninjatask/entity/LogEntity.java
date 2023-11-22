package com.projetofinal.ninjatask.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Document(collection = "log")
public class LogEntity {
    @Id
    private String id;
    private Integer idUsuario;
    private String nomeUsuario;
    private Date dataDeLogin;
}
