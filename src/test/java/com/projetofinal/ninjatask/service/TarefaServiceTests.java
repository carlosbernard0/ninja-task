package com.projetofinal.ninjatask.service;

import com.projetofinal.ninjatask.dto.TarefaDTO;
import com.projetofinal.ninjatask.dto.UsuarioDTO;
import com.projetofinal.ninjatask.dto.UsuarioDTOSemSenha;
import com.projetofinal.ninjatask.entity.TarefaEntity;
import com.projetofinal.ninjatask.entity.UsuarioEntity;
import com.projetofinal.ninjatask.exceptions.BusinessException;
import com.projetofinal.ninjatask.mapper.TarefaMapper;
import com.projetofinal.ninjatask.repository.TarefaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTests {
    @InjectMocks
    private TarefaService tarefaService;

    private TarefaMapper tarefaMapper = Mappers.getMapper(TarefaMapper.class);
    @Mock
    private TarefaRepository tarefaRepository;

    @BeforeEach
    public void init(){
        ReflectionTestUtils.setField(tarefaService, "tarefaMapper", tarefaMapper);
    }
    @Test
    public void deveTestarInserirOuAtualizarComSucesso() throws BusinessException {
        //setup
        TarefaDTO dto = getTarefaDTO();
        TarefaEntity entity = getTarefaEntity();

        //comportamentos
        when(tarefaRepository.save(any())).thenReturn(entity);

        //act
        TarefaDTO retorno = tarefaService.salvarTarefa(dto);

        //assert
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(4, retorno.getIdTarefa());
        Assertions.assertEquals("tarefa de casa", retorno.getNome());
        Assertions.assertEquals("pendente", retorno.getStatus());
        Assertions.assertEquals(getUsuarioDTO, retorno.getUsuario());
    }

    @Test
    public void deveTestarListarComSucesso() throws SQLException {
        //setup
        TarefaEntity tarefaEntity = getTarefaEntity();
        List<TarefaEntity> listaEntities = List.of(tarefaEntity);
        when(tarefaRepository.findAll()).thenReturn(listaEntities);

        //act
        List<TarefaDTO> lista = tarefaService.listarTarefas();

        //assert
        Assertions.assertNotNull(lista);
        Assertions.assertEquals(1,lista.size());
    }
    @Test
    public void deveRemoverComSucesso() throws BusinessException {
        //setup
        TarefaEntity tarefaEntity = getTarefaEntity();
        Optional<TarefaEntity> tarefaEntityOptional = Optional.of(tarefaEntity);
        when(tarefaRepository.findById(anyInt())).thenReturn(tarefaEntityOptional);


        //act
        tarefaService.excluirTarefa(4);

        //assert
        verify(tarefaRepository, times(1)).delete(any());
    }
    @Test
    public void deveTestarRemoverComErro(){
        //setup
        Optional<TarefaEntity> tarefaEntityOptional = Optional.empty();
        when(tarefaRepository.findById(anyInt())).thenReturn(tarefaEntityOptional);

        //act
        Assertions.assertThrows(BusinessException.class, ()-> {
            tarefaService.excluirTarefa(4);
        });

        //assert
    }


    private static TarefaEntity getTarefaEntity(){
        TarefaEntity Entity = new TarefaEntity();
        Entity.setIdTarefa(4);
        Entity.setNome("tarefa de casa");
        Entity.setStatus("pendente");
        Entity.setUsuario(getUsuarioEntity);
        return Entity;
    }

    private static TarefaDTO getTarefaDTO(){
        TarefaDTO dto = new TarefaDTO();
        dto.setIdTarefa(4);
        dto.setNome("tarefa de casa");
        dto.setStatus("pendente");
        dto.setUsuario(getUsuarioDTO);
        return dto;
    }
    static UsuarioEntity getUsuarioEntity = getUsuarioEntity();
    static UsuarioDTO getUsuarioDTO = getUsuarioDTO();

    private static UsuarioDTO getUsuarioDTO(){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(4);
        dto.setNomeUsuario("Henrique");
        dto.setSenhaUsuario("senha123");
        dto.setEmailUsuario("henrique@gmail.com");
        dto.setDataRegistro(new Date(2023-10-07));
        dto.setAtivo(true);
        return dto;
    }

    private static UsuarioEntity getUsuarioEntity(){
        UsuarioEntity entity = new UsuarioEntity();
        entity.setIdUsuario(4);
        entity.setNomeUsuario("Henrique");
        entity.setSenhaUsuario("senha123");
        entity.setEmailUsuario("henrique@gmail.com");
        entity.setDataRegistro(new Date(2023-10-07));
        entity.setAtivo(true);
        return entity;
    }
}
