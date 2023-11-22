package com.projetofinal.ninjatask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaginaDTO<T> {
    private Long totalElementos;
    private Integer totalPaginas;
    private Integer pagina;
    private Integer tamanho;
    private List<T> elementos;
}