package com.projetofinal.ninjatask.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TarefaLogDTO {
    private OperacaoTarefa operacaoTarefa;
    private TarefaDTO tarefaDTO;
    private Date horario;
}
