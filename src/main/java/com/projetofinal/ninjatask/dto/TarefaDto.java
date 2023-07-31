package com.projetofinal.ninjatask.dto;

import com.projetofinal.ninjatask.entity.Caderno;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TarefaDto {
    @Schema(description = "codigo indentificador da tarefa", example = "1")
    private Integer idTarefaDto;

    @Schema(description = "nome da tarefa", example = "tarefa de casa")
    private String nome;

    @Schema(description = "status da tarefa", example = "pendente")
    private String status;

    private CadernoDto cadernoDto;
}
