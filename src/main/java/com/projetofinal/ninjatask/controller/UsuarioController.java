package com.projetofinal.ninjatask.controller;

import com.projetofinal.ninjatask.entity.Usuario;
import com.projetofinal.ninjatask.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/usuario") //localhost:8080/usuario
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @GetMapping("/hello")
    public String metodoTeste(){
        return "Hello World!";
    }
    @PostMapping
    public Usuario inserirUsuario(@RequestBody Usuario usuario) throws Exception{
        return usuarioService.salvarUsuario(usuario);
//        return usuarioSalvo;
    }

    @GetMapping
    public List<Usuario> retornarTodosUsuarios() throws SQLException {
        return usuarioService.listar();
//        return lista;
    }

    @PutMapping
    public boolean atualizarUsuario(@RequestBody Usuario usuario) throws Exception {
        return usuarioService.editarUsuario(usuario);
//        return usuarioEditado;
    }

    @DeleteMapping("/{id_usuario}")
    public boolean removerUsuario(@PathVariable("id_usuario") Integer id){
        return usuarioService.excluirUsuario(id);
//        return removido;
    }
}
