package com.projetofinal.ninjatask.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.projetofinal.ninjatask.dto.*;
import com.projetofinal.ninjatask.entity.TarefaEntity;
import com.projetofinal.ninjatask.exceptions.BusinessException;
import com.projetofinal.ninjatask.mapper.TarefaMapper;
import com.projetofinal.ninjatask.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TarefaService {
    private final UsuarioService usuarioService;
    private final ProdutorService produtorService;
    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;

    public TarefaDTO salvarTarefa(TarefaDTO dto) throws BusinessException, JsonProcessingException {
        UsuarioDTO usuarioDTO = usuarioService.recuperarUsuarioLogado();
        dto.setUsuario(usuarioDTO);

        //converter para entity
        TarefaEntity entity = tarefaMapper.toEntity(dto);

//        salvar no banco
        TarefaEntity salvo = tarefaRepository.save(entity);

        //converter para dto
        TarefaDTO salvoDto = tarefaMapper.toDto(salvo);

        //Salvar no TAREFA-LOG
        salvoDto.getUsuario().setSenhaUsuario(null);
        salvarLog(salvoDto,"CRIACAO");

        return salvoDto;
//        return null;
    }
    public TarefaDTO editarTarefa(TarefaDTO dto) throws BusinessException, JsonProcessingException {
        UsuarioDTO usuarioDTO = usuarioService.recuperarUsuarioLogado();
        dto.setUsuario(usuarioDTO);

        //converter para entity
        TarefaEntity entity = tarefaMapper.toEntity(dto);

//        salvar no banco
        TarefaEntity salvo = tarefaRepository.save(entity);

        //converter para dto
        TarefaDTO salvoDto = tarefaMapper.toDto(salvo);

        //Salvar no TAREFA-LOG
        salvoDto.getUsuario().setSenhaUsuario(null);
        salvarLog(salvoDto,"EDICAO");

        return salvoDto;
//        return null;
    }

    public List<TarefaDTO> listarTarefas()throws SQLException {
        List<TarefaEntity> listaTarefas = tarefaRepository.findAll();
        List<TarefaDTO> listaDtos = listaTarefas.stream()
                .map(entity -> tarefaMapper.toDto((TarefaEntity) entity))
                .toList();
        return listaDtos;
    }

    public List<TarefaDTO> listarPorId(Integer id) throws SQLException, JsonProcessingException {
        Optional<TarefaEntity> listaTarefas = tarefaRepository.findById(id);
        List<TarefaDTO> listaDtos = listaTarefas.stream()
                .map(entity -> tarefaMapper.toDto(entity))
                .toList();

        //salvar na log
        TarefaDTO tarefaDto = tarefaMapper.toDto(listaTarefas.get());
        tarefaDto.getUsuario().setSenhaUsuario(null);
        salvarLog(tarefaDto,"LISTAGEM");

        return listaDtos;
    }

    public TarefaEntity buscarPorId(Integer id) throws BusinessException {
        return tarefaRepository.findById(id)
                .orElseThrow(()-> new BusinessException("Mentoria não existe")); // caso colocar id: 4 vai retornar a mensagem
    }
    public void excluirTarefa(Integer id) throws BusinessException, JsonProcessingException {
        TarefaEntity tarefa = buscarPorId(id);
        tarefaRepository.delete(tarefa);


        //Transformar em DTO e salvar
        TarefaDTO exDTO = tarefaMapper.toDto(tarefa);
        exDTO.getUsuario().setSenhaUsuario(null);
        salvarLog(exDTO,"REMOCAO");
    }

    public PaginaDTO<TarefaDTO> listarPaginado(Integer paginaSolicitada, Integer tamanhoPorPagina){
        PageRequest pageRequest = PageRequest.of(paginaSolicitada, tamanhoPorPagina);
        Page<TarefaEntity> paginaRecuperada = tarefaRepository.findAll(pageRequest);
        return new PaginaDTO<>(paginaRecuperada.getTotalElements(),
                paginaRecuperada.getTotalPages(),
                paginaSolicitada,
                tamanhoPorPagina,
                paginaRecuperada.getContent().stream().map(entity -> tarefaMapper.toDto(entity)).toList());
    }
    public void salvarLog(TarefaDTO tarefaDTO,String operacao) throws JsonProcessingException {
        TarefaLogDTO tarefaLogDTO = new TarefaLogDTO();
        tarefaLogDTO.setTarefaDTO(tarefaDTO);
        tarefaLogDTO.setHorario(new Date());
        tarefaLogDTO.setOperacaoTarefa(OperacaoTarefa.valueOf(operacao));

        produtorService.enviarMensagemAoTopico(tarefaLogDTO);
    }


}
