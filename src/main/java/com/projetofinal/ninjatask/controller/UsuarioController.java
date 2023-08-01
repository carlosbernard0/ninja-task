package com.projetofinal.ninjatask.controller;

import com.projetofinal.ninjatask.dto.UsuarioDto;
import com.projetofinal.ninjatask.entity.Usuario;
import com.projetofinal.ninjatask.exceptions.BusinessException;
import com.projetofinal.ninjatask.service.EmailService;
import com.projetofinal.ninjatask.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/usuario") //localhost:8080/usuario
@RequiredArgsConstructor
@Validated
public class UsuarioController {
    private final EmailService emailService;

    private final UsuarioService usuarioService;

    @Value("${ambiente.api.nome}")
    private String nomeApi;
    @GetMapping("/hello")
    public String metodoTeste(){
        return nomeApi;
    }

    @PostMapping("/email-simples")
    public void enviarEmailSimples(String para, String assunto, String texto) {
        this.emailService.enviarEmailSimples(para, assunto, texto);
    }

    @Operation(summary = "inserir novo usuario", description = "este processo cria um novo usuario na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados"),
            @ApiResponse(responseCode = "500", description = "deu erro no servidor")
    })
    @PostMapping
    public UsuarioDto inserirUsuario(@RequestBody @Valid UsuarioDto usuario) throws BusinessException {
        UsuarioDto usuarioSalvo = usuarioService.salvarUsuario(usuario);
        return usuarioSalvo;
    }


    @Operation(summary = "visualizar usuarios", description = "este processo mostra os usuarios na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "500", description = "deu erro no servidor")
    })
    @GetMapping
    public List<UsuarioDto> retornarTodosUsuarios() throws SQLException {
        List<UsuarioDto> lista = usuarioService.listar();
        return lista;
    }

    @PutMapping
    public boolean atualizarUsuario(@RequestBody @Valid UsuarioDto usuario) throws BusinessException {
        return usuarioService.editarUsuario(usuario);
//        return usuarioEditado;
    }

    @DeleteMapping("/{id_usuario}")
    public boolean removerUsuario(@PathVariable("id_usuario") Integer id){
        return usuarioService.excluirUsuario(id);
//        return removido;
    }
}
