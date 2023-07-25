package com.projetofinal.ninjatask.controller;


import com.projetofinal.ninjatask.entity.Tarefa;
import com.projetofinal.ninjatask.entity.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@Controller
@RequestMapping("/tarefa")
public class TarefaController {

//    @GetMapping
//    public Tarefa mostrarTarefa(){
//
//
//        return ;
//    }


//    @PostMapping
//    public Tarefa inserir(@RequestBody Usuario usuario) throws Exception{
//        Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);
//        return usuarioSalvo;
//    }
//
//    @GetMapping
//    public List<Usuario> retornarTodosUsuarios() throws SQLException {
//        List<Usuario> lista= usuarioService.listar();
//        return lista;
//    }
//
//    @PutMapping
//    public boolean atualizarUsuario(@RequestBody Usuario usuario) throws Exception {
//        boolean usuarioEditado =usuarioService.editarUsuario(usuario);
//        return usuarioEditado;
//    }
//
//    @DeleteMapping("/{id_usuario}")
//    public boolean removerUsuario(@PathVariable("id_usuario") Integer id){
//        boolean removido = usuarioService.excluirUsuario(id);
//        return removido;
//    }
}
