package com.projetofinal.ninjatask.service;

import com.projetofinal.ninjatask.dto.TarefaDto;
import com.projetofinal.ninjatask.entity.CadernoEntity;
import com.projetofinal.ninjatask.entity.TarefaEntity;
import com.projetofinal.ninjatask.exceptions.BusinessException;
import com.projetofinal.ninjatask.mapper.TarefaMapper;
import com.projetofinal.ninjatask.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;


    public TarefaDto salvarTarefa(TarefaDto dto){

        //converter para entity
        TarefaEntity entity = tarefaMapper.toEntity(dto);

//        salvar no banco
        TarefaEntity salvo = tarefaRepository.save(entity);

        //converter para dto
        TarefaDto salvoDto = tarefaMapper.toDto(salvo);

        return salvoDto;
//        return null;
    }

    public List<TarefaDto> listarTarefas()throws SQLException {
        List<TarefaEntity> listaTarefas = tarefaRepository.findAll();
        List<TarefaDto> listaDtos = listaTarefas.stream()
                .map(entity -> tarefaMapper.toDto((TarefaEntity) entity))
                .toList();
        return listaDtos;
//        return null;
    }

    //FAZER O LISTAR POR CADERNO

//    public List<TarefaDto> listarPorCaderno(Integer idCaderno) throws SQLException {
////        List<TarefaEntity> lista = tarefaRepository.findById(idCaderno);
//        List<TarefaDto> listarDto = lista.stream()
//                .map(entidade -> tarefaMapper.toDto(entidade))
//                .toList();
//        return listarDto;
//    }


    public TarefaDto buscarPorIdDto(Integer id) throws BusinessException{
        TarefaEntity entity = buscarPorId(id);
        return tarefaMapper.toDto(entity);
    }

    private TarefaEntity buscarPorId(Integer id) throws BusinessException {
        return tarefaRepository.findById(id)
                .orElseThrow(()-> new BusinessException("Mentoria não existe")); // caso colocar id: 4 vai retornar a mensagem
    }




    public void excluirTarefa(Integer id){
        tarefaRepository.deleteById(id);
    }


}
