package com.projetofinal.ninjatask.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa {


    private Integer idTarefa;
    private String nome;
    private String status;
    private Caderno caderno;
//    private Integer idCaderno;



}
