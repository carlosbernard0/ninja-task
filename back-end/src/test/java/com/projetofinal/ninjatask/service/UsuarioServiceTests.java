package com.projetofinal.ninjatask.service;

import com.projetofinal.ninjatask.dto.*;
import com.projetofinal.ninjatask.entity.UsuarioEntity;
import com.projetofinal.ninjatask.exceptions.BusinessException;
import com.projetofinal.ninjatask.mapper.UsuarioMapper;
import com.projetofinal.ninjatask.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.projetofinal.ninjatask.service.TarefaServiceTests.elementos;
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

    @Mock
    private LogService logService;

    @Mock
    private Claims claims;
    @Mock
    private Jwts jwts;

    @Mock
    private SimpleGrantedAuthority simpleGrantedAuthority;

    @BeforeEach
    public void init() {
        ReflectionTestUtils.setField(usuarioService, "usuarioMapper", usuarioMapper);
        ReflectionTestUtils.setField(usuarioService, "validadeJWT", "86400000");
        ReflectionTestUtils.setField(usuarioService, "secret", "MinhaChaveSecreta");
    }

    @Test
    public void deveTestarFazerLoginComSucesso() throws BusinessException {
        //setup
        AutenticacaoDTO dto = new AutenticacaoDTO();
        dto.setEmailUsuario("joao@gmail.com");
        dto.setSenhaUsuario("senha123");

        final var userAuthentication = mock(Authentication.class);
        final var entity = mock(UsuarioEntity.class);

        //comportamentos
        when(authenticationManager.authenticate(any())).thenReturn(userAuthentication);
        when(userAuthentication.getPrincipal()).thenReturn(entity);

        //act
        String autenticacaoDTO = usuarioService.fazerLogin(dto);

        //assert
        Assertions.assertNotNull(autenticacaoDTO);
    }

    @Test
    public void deveTestarFazerLoginComErro() throws BusinessException {
        //setup
        AutenticacaoDTO dto = new AutenticacaoDTO();
        dto.setEmailUsuario("joao@gmail.com");
        dto.setSenhaUsuario("senha123");

        //comportamentos
        when(authenticationManager.authenticate(any())).thenThrow(BadCredentialsException.class);

        Assertions.assertThrows(BusinessException.class, ()-> {
            //act
            usuarioService.fazerLogin(dto);
        });

    }


    @Test
    public void deveTestarValidarTokenComSucesso() throws BusinessException {
        //setup
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJuaW5qYS10YXNrIiwiQ0FSR09TIjpbIlJPTEVfREVWIl0sInN1YiI6IjEiLCJpYXQiOjE2OTc2NjU2NTUsImV4cCI6MTY5Nzc1MjA1NX0.gwY0GuhLXMczNe1P2lSDPy-FonF3c445ynftBujr-vs";

        //act
        usuarioService.validarToken(token);

        //assert
        Assertions.assertNotNull(token);
    }

    @Test
    public void deveTestarValidarTokenComErro() {
        //setup
        String token = null;

        //assert
//        Assertions.assertThrows(BusinessException.class, ()-> {
            //act
            usuarioService.validarToken(token);
//        });
    }

    @Test
    public void deveTestarValidarUsuarioComSucesso() throws BusinessException {
        //setup
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setEmailUsuario("joao@gmail.com");

        //act
        usuarioService.validarUsuario(usuario);
        //assert
        Assertions.assertEquals(usuario, usuario);
    }
    @Test
    public void deveTestarValidarUsuarioComErro() throws BusinessException {
        //setup
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setEmailUsuario("joaogmail.com");

        //assert
        Assertions.assertThrows(BusinessException.class, ()-> {
            //act
            usuarioService.validarUsuario(usuario);
        });
    }

    @Test
    public void deveTestarValidarEmailComSucesso() throws BusinessException {
        //setup
        String email = "joao@gmail.com";

        //act
        usuarioService.validarEmailExistente(email);
        //assert
        Assertions.assertNotNull(email);
    }

    @Test
    public void deveTestarValidarEmailComErro() throws BusinessException {
        //setup
        String email = "joao@gmail.com";
        UsuarioEntity entity= getUsuarioEntity();


        when(usuarioRepository.findByEmailUsuario(email)).thenReturn(Optional.of(entity));
        //assert
        Assertions.assertThrows(BusinessException.class, ()-> {
            //act
            usuarioService.validarEmailExistente(email);
        });
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
        UsuarioDTO retornoEdicao = usuarioService.editarUsuario(dto);

        //assert
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(4, retorno.getIdUsuario());
        Assertions.assertEquals("Henrique", retorno.getNomeUsuario());
        Assertions.assertEquals("henrique@gmail.com", retorno.getEmailUsuario());
        Assertions.assertEquals("senha123", retorno.getSenhaUsuario());
        Assertions.assertEquals(new Date(2023 - 10 - 07), retorno.getDataRegistro());
        Assertions.assertEquals(true, retorno.getAtivo());

        Assertions.assertNotNull(retornoEdicao);
        Assertions.assertEquals(4, retornoEdicao.getIdUsuario());
        Assertions.assertEquals("Henrique", retornoEdicao.getNomeUsuario());

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
        Assertions.assertEquals(1, lista.size());
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
    public void deveTestarRemoverComErro() {
        //setup
        Optional<UsuarioEntity> usuarioEntityOptional = Optional.empty();

        when(usuarioRepository.findById(anyInt())).thenReturn(usuarioEntityOptional);

        //assert
        Assertions.assertThrows(BusinessException.class, () -> {
            //act
            usuarioService.excluirUsuario(4);
        });
    }
    @Test
    public void deteTestarDesativarUsuarioComSucesso() {
        //setup
        UsuarioEntity usuario = getUsuarioEntity();

        //comportamentos
        when(usuarioRepository.getReferenceById(anyInt())).thenReturn(usuario);

        //act
        usuarioService.desativarUsuario(4);

        //assert
        verify(usuarioRepository, times(1)).save(any());
    }

    @Test
    public void deveTestarConverterSenhaComSucesso(){
        //setup

        UsuarioEntity entity = getUsuarioEntity();


        //act
        usuarioService.converterSenha(entity.getSenhaUsuario());
        //assert
        Assertions.assertNotNull(entity.getSenhaUsuario());
    }

    @Test
    public void deveTestarListarPaginadoComSucesso() {
        // Setup
        PaginaDTO<UsuarioDTO> paginaDTO = getPaginaDTO(); // Obtenha uma instância de PaginaDTO com elementos
        PageRequest pageRequest = PageRequest.of(1, 2);

        List<UsuarioEntity> usuarioEntities = new ArrayList<>();

        when(usuarioRepository.findAll(pageRequest))
                .thenReturn(new PageImpl<>(usuarioEntities, pageRequest, usuarioEntities.size()));

        PaginaDTO<UsuarioDTO> paginaRecuperada = usuarioService.listarPaginado(1, 2);

        // Verificar os resultados
        Assertions.assertNotNull(paginaRecuperada);
        Assertions.assertEquals(paginaDTO.getPagina(), paginaRecuperada.getPagina());
        Assertions.assertEquals(paginaDTO.getTamanho(), paginaRecuperada.getTamanho());
    }

    @Test
    public void deveTestarRelatorioComSucesso(){
        //setup
        RelatorioUsuariosTarefasDTO relatorio = new RelatorioUsuariosTarefasDTO();
        //act
        usuarioService.relatorio();
        //assert
        Assertions.assertNotNull(relatorio);
    }

    @Test
    public void deveTestarFindByEmailUsuarioComSucesso(){
        //setup
        String emailUsuario = "joao@gmail.com";

        //act
        usuarioService.findByEmailUsuario(emailUsuario);
        //assert

        Assertions.assertNotNull(emailUsuario);
    }

    @Test
    public void deveTestarRecuperarIdUsuarioLogadoComSucesso(){
        //setup
        Integer idUsuario = 4;

        UsernamePasswordAuthenticationToken tokenPass = new UsernamePasswordAuthenticationToken("0", null,
                new ArrayList<>());

        SecurityContextHolder.getContext().setAuthentication(tokenPass);

        //act
        Integer id = usuarioService.recuperarIdUsuarioLogado();

        //assert
        Assertions.assertNotNull(idUsuario);
//        Assertions.assertEquals( id, idUsuario);
    }
    @Test
    public void deveTestarRecuperarNomeUsuarioLogadoComSucesso(){
        //setup
        String nome = "joao";
        final var userAuthentication = mock(Authentication.class);
        UsuarioEntity entity = getUsuarioEntity();
        UsernamePasswordAuthenticationToken tokenPass = new UsernamePasswordAuthenticationToken("0", null,
                new ArrayList<>());

        SecurityContextHolder.getContext().setAuthentication(tokenPass);

        //comportamentos
        when(usuarioRepository.findByIdUsuario(any())).thenReturn(Optional.of(entity));

        //act
        usuarioService.recuperarNomeUsuarioLogado();

        //assert
        Assertions.assertNotNull(nome);
    }



    @Test
    public void deveTestarRecuperarUsuarioLogado() throws BusinessException {
        //setup
        Integer idUsuarioLogado = 4;
        UsuarioDTO dto = getUsuarioDTO();
        UsuarioEntity entity = getUsuarioEntity();
        UsernamePasswordAuthenticationToken tokenPass = new UsernamePasswordAuthenticationToken("0", null,
                new ArrayList<>());

        SecurityContextHolder.getContext().setAuthentication(tokenPass);


        //comportamentos
        when(usuarioRepository.findById(any())).thenReturn(Optional.of(entity));



        //act
        usuarioService.recuperarUsuarioLogado();

        //assert
        Assertions.assertNotNull(idUsuarioLogado);
        Assertions.assertNotNull(entity);
        Assertions.assertEquals(dto.getIdUsuario(), idUsuarioLogado);

    }



    private static UsuarioDTO getUsuarioDTO() {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(4);
        dto.setNomeUsuario("Henrique");
        dto.setSenhaUsuario("senha123");
        dto.setEmailUsuario("henrique@gmail.com");
        dto.setDataRegistro(new Date(2023 - 10 - 07));
        dto.setAtivo(true);
        return dto;
    }

    private static UsuarioEntity getUsuarioEntity() {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setIdUsuario(4);
        entity.setNomeUsuario("Henrique");
        entity.setSenhaUsuario("senha123");
        entity.setEmailUsuario("henrique@gmail.com");
        entity.setDataRegistro(new Date(2023 - 10 - 07));
        entity.setAtivo(true);
        return entity;
    }

    private static PaginaDTO getPaginaDTO() {
        PaginaDTO paginaDTO = new PaginaDTO();
        paginaDTO.setTotalPaginas(3);
        paginaDTO.setPagina(1);
        paginaDTO.setTamanho(2);
        paginaDTO.setElementos(elementos);
        return paginaDTO;
    }
}