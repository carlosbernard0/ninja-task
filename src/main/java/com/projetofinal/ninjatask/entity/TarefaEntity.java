package com.projetofinal.ninjatask.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TarefaEntity {


    private Integer idTarefa;
    private String nome;
    private String status;
    private CadernoEntity cadernoEntity;
//    private Integer idCaderno;



}
