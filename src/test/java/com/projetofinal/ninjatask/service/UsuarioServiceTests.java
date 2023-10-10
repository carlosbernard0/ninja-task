package com.projetofinal.ninjatask.service;

import com.projetofinal.ninjatask.dto.AutenticacaoDTO;
import com.projetofinal.ninjatask.dto.UsuarioDTO;
import com.projetofinal.ninjatask.dto.UsuarioDTOSemSenha;
import com.projetofinal.ninjatask.entity.UsuarioEntity;
import com.projetofinal.ninjatask.exceptions.BusinessException;
import com.projetofinal.ninjatask.mapper.UsuarioMapper;
import com.projetofinal.ninjatask.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTests {
    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private Authentication authentication;

    private UsuarioMapper usuarioMapper = Mappers.getMapper(UsuarioMapper.class);

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void init(){
        ReflectionTestUtils.setField(usuarioService, "usuarioMapper", usuarioMapper);
    }

    @Test
    public void deveTestarFazerLoginComSucesso() throws BusinessException {
        //setup
        AutenticacaoDTO dto = new AutenticacaoDTO();
        dto.setEmailUsuario("joao@gmail.com");
        dto.setSenhaUsuario("senha123");

        final var dataExpiracao= mock(java.sql.Date.class);


        final var userAuthentication = mock(Authentication.class);
        final var entity = mock(UsuarioEntity.class);

        //comportamentos
        when(authenticationManager.authenticate(any())).thenReturn(userAuthentication);
        when(userAuthentication.getPrincipal()).thenReturn(entity);

        //falta continuar o Date


        //act
        String autenticacaoDTO = usuarioService.fazerLogin(dto);

        //assert
//        Assertions.assertNotNull(autenticacaoDTO);
    }

    @Test
    public void deveTestarInserirOuAtualizarComSucesso() throws BusinessException {
        //setup
        UsuarioDTO dto = getUsuarioDTO();


        UsuarioEntity entity = getUsuarioEntity();

        //comportamentos
        when(usuarioRepository.save(any())).thenReturn(entity);

        //act
        UsuarioDTO retorno = usuarioService.salvarUsuario(dto);

        //assert
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(4, retorno.getIdUsuario());
        Assertions.assertEquals("Henrique", retorno.getNomeUsuario());
        Assertions.assertEquals("henrique@gmail.com", retorno.getEmailUsuario());
        Assertions.assertEquals("senha123", retorno.getSenhaUsuario());
        Assertions.assertEquals(new Date(2023-10-07), retorno.getDataRegistro());
        Assertions.assertEquals(true, retorno.getAtivo());

    }

    @Test
    public void deveTestarListarComSucesso() throws SQLException {
        //setup
        UsuarioEntity usuarioEntity = getUsuarioEntity();
        List<UsuarioEntity> listaEntities = List.of(usuarioEntity);
        when(usuarioRepository.findAll()).thenReturn(listaEntities);

        //act
        List<UsuarioDTOSemSenha> lista = usuarioService.listar();

        //assert
        Assertions.assertNotNull(lista);
        Assertions.assertEquals(1,lista.size());
    }

    @Test
    public void deveRemoverComSucesso() throws BusinessException {
        //setup
        UsuarioEntity usuarioEntity = getUsuarioEntity();
        Optional<UsuarioEntity> usuarioEntityOptional = Optional.of(usuarioEntity);
        when(usuarioRepository.findById(anyInt())).thenReturn(usuarioEntityOptional);


        //act
        usuarioService.excluirUsuario(4);

        //assert
        verify(usuarioRepository, times(1)).delete(any());
    }

    @Test
    public void deveTestarRemoverComErro(){
        //setup
        Optional<UsuarioEntity> usuarioEntityOptional = Optional.empty();
        when(usuarioRepository.findById(anyInt())).thenReturn(usuarioEntityOptional);

        //assert
        Assertions.assertThrows(BusinessException.class, ()-> {
            //act
            usuarioService.excluirUsuario(4);
        });
    }

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
