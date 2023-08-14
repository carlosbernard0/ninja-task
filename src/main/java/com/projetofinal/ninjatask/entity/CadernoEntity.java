package com.projetofinal.ninjatask.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

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

    //muitos CADERNOS para um USUARIO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private UsuarioEntity usuario;

    //um CADERNO para varias TAREFAS
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "caderno")
    private Set<TarefaEntity> tarefas;
}
