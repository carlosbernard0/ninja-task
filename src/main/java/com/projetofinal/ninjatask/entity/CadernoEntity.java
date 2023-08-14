package com.projetofinal.ninjatask.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Caderno")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CadernoEntity {
    @Id
    @Column(name = "id_caderno")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gerador_caderno")
    @SequenceGenerator(name = "gerador_caderno", sequenceName = "seq_caderno", allocationSize = 1)
    private Integer idCaderno;
    @Column(name = "nome_caderno")
    private String nomeCaderno;

    //Fazer Join depois
    private UsuarioEntity usuarioEntity;
}
