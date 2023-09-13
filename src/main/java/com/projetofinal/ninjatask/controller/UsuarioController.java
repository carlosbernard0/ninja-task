package com.projetofinal.ninjatask.controller;

import com.projetofinal.ninjatask.dto.PaginaDTO;
import com.projetofinal.ninjatask.dto.RelatorioUsuariosCadernosDTO;
import com.projetofinal.ninjatask.dto.UsuarioDTO;
import com.projetofinal.ninjatask.dto.UsuarioDTOSemSenha;
import com.projetofinal.ninjatask.entity.UsuarioEntity;
import com.projetofinal.ninjatask.exceptions.BusinessException;
import com.projetofinal.ninjatask.repository.UsuarioRepository;
import com.projetofinal.ninjatask.service.EmailService;
import com.projetofinal.ninjatask.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/usuario") //localhost:8080/usuario
@RequiredArgsConstructor
@Validated
@Slf4j
public class UsuarioController {
    private final EmailService emailService;
    private final UsuarioService usuarioService;
    private UsuarioRepository usuarioRepository;

    @Value("${ambiente.api.nome}")
    private String nomeApi;

//    @GetMapping("/hello")
//    public String metodoTeste(){
//        return nomeApi;
//    }

    //email simples
//    @PostMapping("/email-simples")
//    public void enviarEmailSimples(String para, String assunto, String texto)throws MessagingException {
//        this.emailService.enviarEmailSimples(para, assunto, texto);
//    }


    @Operation(summary = "enviar email para o usuario", description = "este processo envia um email com template")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados"),
            @ApiResponse(responseCode = "500", description = "deu erro no servidor")
    })
    @PostMapping("/email-com-template")
    public void enviarEmailComTemplate(String para, String assunto, String texto)throws MessagingException {
        this.emailService.enviarEmailComTemplate(para, assunto, texto);
    }


    @Operation(summary = "inserir novo usuario", description = "este processo cria um novo usuario na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados"),
            @ApiResponse(responseCode = "500", description = "deu erro no servidor")
    })
    @PostMapping
    public UsuarioDTO inserirUsuario(@RequestBody @Valid UsuarioDTO usuario) throws BusinessException {
        UsuarioDTO usuarioSalvo = usuarioService.salvarUsuario(usuario);
        return usuarioSalvo;
    }


    @Operation(summary = "visualizar usuarios", description = "este processo mostra os usuarios na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "500", description = "deu erro no servidor")
    })
    //GET ANTIGO QUE RETORNA A SENHA JUNTO
//    @GetMapping
//    public List<UsuarioDTO> retornarTodosUsuarios() throws SQLException {
//        List<UsuarioDTO> lista = usuarioService.listar();
//        return lista;
//    }

    //GET DO MODULO 6 QUE NAO RETORNA A SENHA
    @GetMapping
    public List<UsuarioDTOSemSenha> retornarTodosUsuarios() throws SQLException {
        List<UsuarioDTOSemSenha> lista = usuarioService.listar();
        return lista;
    }

    @Operation(summary = "editar usuario", description = "este processo faz a edição de um usuario na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados"),
            @ApiResponse(responseCode = "500", description = "deu erro no servidor")
    })
    @PutMapping
    public UsuarioDTO atualizarUsuario(@RequestBody @Valid UsuarioDTO usuario) throws BusinessException {
        return usuarioService.editarUsuario(usuario);
    }

    @Operation(summary = "desativar usuario", description = "este processo exclui um usuario na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados"),
            @ApiResponse(responseCode = "500", description = "deu erro no servidor")
    })

//    @DeleteMapping("/{id_usuario}")
//    public void removerUsuario(@PathVariable("id_usuario") Integer id){
//         usuarioService.excluirUsuario(id);
//    }

    @DeleteMapping("/{id_usuario}")
    public void desativarUsuario(@PathVariable("id_usuario") Integer id){
        usuarioService.destivarUsuario(id);
    }

    // FAZENDO COM O Service A PAGINAÇÃO
    @GetMapping("/listarPaginado")
    public PaginaDTO<UsuarioDTO> listarPaginado(Integer paginaSolicitada, Integer tamanhoPorPagina){
        return usuarioService.listarPaginado(paginaSolicitada, tamanhoPorPagina);
    }


    @GetMapping("/relatorio")
    public List<RelatorioUsuariosCadernosDTO> gerarRelatorio(){
        return usuarioService.relatorio();

    }


}
