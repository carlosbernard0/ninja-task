package com.projetofinal.ninjatask.service;
import com.projetofinal.ninjatask.dto.PaginaDTO;
import com.projetofinal.ninjatask.dto.TarefaDTO;
import com.projetofinal.ninjatask.dto.UsuarioDTO;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.SQLException;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TarefaServiceTests {
    static Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
    @InjectMocks
    private TarefaService tarefaService;

    private TarefaMapper tarefaMapper = Mappers.getMapper(TarefaMapper.class);
    @Mock
    private TarefaRepository tarefaRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init(){
        ReflectionTestUtils.setField(tarefaService, "tarefaMapper", tarefaMapper);
    }

    @Test
    public void deveTestarInserirOuAtualizarComSucesso() throws BusinessException {
        //Talvez faremos um Login aqui --

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
    public void deveTestarBuscarPorIdComSucesso() throws SQLException, BusinessException {
        //setup
        TarefaEntity tarefaEntity = getTarefaEntity();
        TarefaDTO tarefaDTO = getTarefaDTO();

        when(tarefaRepository.findById(4)).thenReturn(Optional.of(tarefaEntity));

        //act
        TarefaEntity retornoA = tarefaService.buscarPorId(4);
        TarefaDTO retornoB = tarefaMapper.toDto(retornoA);
        //assert
        Assertions.assertNotNull(retornoB);
        Assertions.assertEquals(retornoB,tarefaDTO);
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

    @Test
    public void deveTestarListarPaginadoComSucesso() {
        // Setup
        PaginaDTO<TarefaDTO> paginaDTO = getPaginaDTO(); // Obtenha uma instância de PaginaDTO com elementos
        PageRequest pageRequest = PageRequest.of(1, 2);

        List<TarefaEntity> tarefaEntities = new ArrayList<>();

        when(tarefaRepository.findAll(pageRequest))
                .thenReturn(new PageImpl<>(tarefaEntities, pageRequest, tarefaEntities.size()));

        PaginaDTO<TarefaDTO> paginaRecuperada = tarefaService.listarPaginado(1, 2);

        // Verificar os resultados
        Assertions.assertNotNull(paginaRecuperada);
        Assertions.assertEquals(paginaDTO.getPagina(), paginaRecuperada.getPagina());
        Assertions.assertEquals(paginaDTO.getTamanho(), paginaRecuperada.getTamanho());
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
        dto.setAtivo(true);
        return dto;
    }

    private static UsuarioEntity getUsuarioEntity(){
        UsuarioEntity entity = new UsuarioEntity();
        entity.setIdUsuario(4);
        entity.setNomeUsuario("Henrique");
        entity.setSenhaUsuario("senha123");
        entity.setEmailUsuario("henrique@gmail.com");
        entity.setAtivo(true);
        return entity;
    }
    static List<TarefaDTO> elementos = new ArrayList<>();

    private static PaginaDTO getPaginaDTO() {
        PaginaDTO paginaDTO = new PaginaDTO();
        paginaDTO.setTotalPaginas(3);
        paginaDTO.setPagina(1);
        paginaDTO.setTamanho(2);
        paginaDTO.setElementos(elementos);

        // Elementos
        TarefaDTO tarefa1 = new TarefaDTO();
        tarefa1.setIdTarefa(1);
        tarefa1.setNome("Tarefa 1");
        tarefa1.setStatus("Concluída");
        tarefa1.setUsuario(getUsuarioDTO());
        elementos.add(tarefa1);

        TarefaDTO tarefa2 = new TarefaDTO();
        tarefa2.setIdTarefa(2);
        tarefa2.setNome("Tarefa 2");
        tarefa2.setStatus("Pendente");
        tarefa2.setUsuario(getUsuarioDTO());
        elementos.add(tarefa2);

        TarefaDTO tarefa3 = new TarefaDTO();
        tarefa1.setIdTarefa(3);
        tarefa1.setNome("Tarefa 1");
        tarefa1.setStatus("Concluída");
        tarefa1.setUsuario(getUsuarioDTO());
        elementos.add(tarefa1);

        TarefaDTO tarefa4 = new TarefaDTO();
        tarefa2.setIdTarefa(4);
        tarefa2.setNome("Tarefa 2");
        tarefa2.setStatus("Pendente");
        tarefa2.setUsuario(getUsuarioDTO());
        elementos.add(tarefa2);

        TarefaDTO tarefa5 = new TarefaDTO();
        tarefa1.setIdTarefa(5);
        tarefa1.setNome("Tarefa 1");
        tarefa1.setStatus("Concluída");
        tarefa1.setUsuario(getUsuarioDTO());
        elementos.add(tarefa1);

        TarefaDTO tarefa6 = new TarefaDTO();
        tarefa2.setIdTarefa(6);
        tarefa2.setNome("Tarefa 2");
        tarefa2.setStatus("Pendente");
        tarefa2.setUsuario(getUsuarioDTO());
        elementos.add(tarefa2);



        return paginaDTO;
    }
}
