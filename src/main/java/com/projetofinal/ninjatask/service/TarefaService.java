package com.projetofinal.ninjatask.service;

import com.projetofinal.ninjatask.dto.PaginaDTO;
import com.projetofinal.ninjatask.dto.TarefaDTO;
import com.projetofinal.ninjatask.dto.UsuarioDTO;
import com.projetofinal.ninjatask.entity.TarefaEntity;
import com.projetofinal.ninjatask.entity.UsuarioEntity;
import com.projetofinal.ninjatask.exceptions.BusinessException;
import com.projetofinal.ninjatask.mapper.TarefaMapper;
import com.projetofinal.ninjatask.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final UsuarioService usuarioService;
    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;

    public TarefaDTO salvarTarefa(TarefaDTO dto) throws BusinessException {
        UsuarioDTO usuarioDTO = usuarioService.validarIdLogado();
        dto.setUsuario(usuarioDTO);

        //converter para entity
        TarefaEntity entity = tarefaMapper.toEntity(dto);

//        salvar no banco
        TarefaEntity salvo = tarefaRepository.save(entity);

        //converter para dto
        TarefaDTO salvoDto = tarefaMapper.toDto(salvo);

        return salvoDto;
//        return null;
    }

    public List<TarefaDTO> listarTarefas()throws SQLException {
        List<TarefaEntity> listaTarefas = tarefaRepository.findAll();
        List<TarefaDTO> listaDtos = listaTarefas.stream()
                .map(entity -> tarefaMapper.toDto((TarefaEntity) entity))
                .toList();
        return listaDtos;
//        return null;
    }

//    FAZER O LISTAR POR CADERNO
//
//    public TarefaDto listarPorCadernoDto(Integer caderno) throws SQLException {
//        List<TarefaEntity> entity = listarPorCaderno(caderno);
//        return tarefaMapper.toDto((TarefaEntity) entity);
//    }
//
//    private List<TarefaEntity> listarPorCaderno(Integer caderno) throws SQLException {
//        return tarefaRepository.findBycaderno(caderno);
//    }

    public TarefaDTO buscarPorIdDto(Integer id) throws BusinessException{
        TarefaEntity entity = buscarPorId(id);
        return tarefaMapper.toDto(entity);
    }

    private TarefaEntity buscarPorId(Integer id) throws BusinessException {
        return tarefaRepository.findById(id)
                .orElseThrow(()-> new BusinessException("Mentoria não existe")); // caso colocar id: 4 vai retornar a mensagem
    }

    public void excluirTarefa(Integer id) throws BusinessException {
        TarefaEntity tarefa = buscarPorId(id);
        tarefaRepository.delete(tarefa);
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

}
