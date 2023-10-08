package com.projetofinal.ninjatask.service;

import com.projetofinal.ninjatask.dto.UsuarioDTO;
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
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.time.LocalDate;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTests {
    @InjectMocks
    private UsuarioService usuarioService;

    private UsuarioMapper usuarioMapper = Mappers.getMapper(UsuarioMapper.class);

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void init(){
        ReflectionTestUtils.setField(usuarioService, "usuarioMapper", usuarioMapper);
    }
    @Test
    public void deveTestarInserirOuAtualizarComSucesso() throws BusinessException {
        //setup
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(10);
        dto.setNomeUsuario("Henrique");
        dto.setEmailUsuario("henrique@gmail.com");
        dto.setSenhaUsuario("senha123");
        dto.setDataRegistro(new Date(2023-10-07));
        dto.setAtivo(true);

        UsuarioEntity entity = new UsuarioEntity();
        entity.setIdUsuario(10);
        entity.setNomeUsuario("Henrique");
        entity.setEmailUsuario("henrique@gmail.com");
        entity.setSenhaUsuario("senha123");
        entity.setDataRegistro(new Date(2023-10-07));
        entity.setAtivo(true);

        //comportamentos
        when(usuarioRepository.save(any())).thenReturn(entity);

        //act
        UsuarioDTO retorno = usuarioService.salvarUsuario(dto);

        //assert
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(10, retorno.getIdUsuario());
        Assertions.assertEquals("Henrique", retorno.getNomeUsuario());
        Assertions.assertEquals("henrique@gmail.com", retorno.getEmailUsuario());
        Assertions.assertEquals("senha123", retorno.getSenhaUsuario());
        Assertions.assertEquals(new Date(2023-10-07), retorno.getDataRegistro());
        Assertions.assertEquals(true, retorno.getAtivo());

    }
}
