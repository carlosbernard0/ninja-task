package com.projetofinal.ninjatask.service;

import com.projetofinal.ninjatask.dto.TarefaDto;
import com.projetofinal.ninjatask.entity.Tarefa;
import com.projetofinal.ninjatask.mapper.TarefaMapper;
import com.projetofinal.ninjatask.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;


    public TarefaDto salvarTarefa(TarefaDto tarefaDto){

        //converter para entity
        Tarefa tarefaConvertida = tarefaMapper.converterParaEntity(tarefaDto);

//        salvar no banco
        Tarefa tarefaSalva = tarefaRepository.criarTarefa(tarefaConvertida);

        //converter para dto
        TarefaDto tarefaRetornada = tarefaMapper.converterParaDto(tarefaSalva);

        return tarefaRetornada;
//        return null;
    }

    public List<TarefaDto> listarTarefas()throws SQLException {
        List<TarefaDto> listaDtos = this.tarefaRepository.listarTarefas().stream()
                .map(entity -> tarefaMapper.converterParaDto(entity))
                .toList();
        return listaDtos;
//        return null;
    }

    public boolean editarTarefa(TarefaDto tarefaDto) throws Exception {
        Tarefa tarefaConvertida = tarefaMapper.converterParaEntity(tarefaDto);
//
        return tarefaRepository.editarTarefa(tarefaConvertida);
//            return false;
    }

    public boolean excluirTarefa(Integer idTarefa){
        return this.tarefaRepository.excluirTarefa(idTarefa);
    }


}
