package com.projetofinal.ninjatask.service;

import com.projetofinal.ninjatask.dto.TarefaDto;
import com.projetofinal.ninjatask.entity.TarefaEntity;
import com.projetofinal.ninjatask.mapper.TarefaMapper;
import lombok.RequiredArgsConstructor;
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
        TarefaEntity tarefaEntityConvertida = tarefaMapper.converterParaEntity(tarefaDto);

//        salvar no banco
        TarefaEntity tarefaEntitySalva = tarefaRepository.criarTarefa(tarefaEntityConvertida);

        //converter para dto
        TarefaDto tarefaRetornada = tarefaMapper.converterParaDto(tarefaEntitySalva);

        return tarefaRetornada;
//        return null;
    }

    public List<TarefaDto> listarTarefas()throws SQLException {
        List<TarefaDto> listaDtos = this.tarefaRepository.listarTarefas().stream()
                .map(entity -> tarefaMapper.converterParaDto((TarefaEntity) entity))
                .toList();
        return listaDtos;
//        return null;
    }
    public List<TarefaDto> listarPorCaderno(Integer idCaderno) throws SQLException {
        List<TarefaDto> listar = this.tarefaRepository.listarTarefasPorCaderno(idCaderno).stream()
                .map(entidade -> tarefaMapper.converterParaDto(entidade))
                .toList();
        return listar;
    }


    public boolean editarTarefa(TarefaDto tarefaDto) throws Exception {
        TarefaEntity tarefaEntityConvertida = tarefaMapper.converterParaEntity(tarefaDto);
//
        return tarefaRepository.editarTarefa(tarefaEntityConvertida);
//            return false;
    }

    public boolean excluirTarefa(Integer idTarefa){
        return this.tarefaRepository.excluirTarefa(idTarefa);
    }


}
