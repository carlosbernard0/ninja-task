package com.projetofinal.ninjatask.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Tarefa")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TarefaEntity {

    @Id
    @Column(name = "id_tarefa")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gerador_tarefa")
    @SequenceGenerator(name = "gerador_tarefa", sequenceName = "seq_tarefa", allocationSize = 1)
    private Integer idTarefa;
    @Column(name = "nome_tarefa")
    private String nome;
    @Column(name = "status_tarefa")
    private String status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private UsuarioEntity usuario;


}
