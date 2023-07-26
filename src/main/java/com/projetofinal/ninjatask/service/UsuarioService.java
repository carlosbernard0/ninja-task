package com.projetofinal.ninjatask.service;

import com.projetofinal.ninjatask.entity.Usuario;
import com.projetofinal.ninjatask.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioService(){
        usuarioRepository = new UsuarioRepository();
    }
    public void validarUsuario(Usuario usuario) throws Exception {
        if (!usuario.getEmail_usuario().contains("@")){
            throw new Exception("Precisa ter @");
        }
    }
    public Usuario salvarUsuario(Usuario usuario) throws Exception{
        validarUsuario(usuario);

        Usuario usuarioSalvo = usuarioRepository.cadastrarUsuario(usuario);

        System.out.println("Usuario cadastrado service!");
        return usuarioSalvo;
    }

    public List<Usuario> listar() throws SQLException {
        List<Usuario> lista = usuarioRepository.listarUsuario();
        lista.stream().forEach(System.out::println);
        return lista;
    }

    public boolean editarUsuario(Usuario usuario) throws Exception {
        validarUsuario(usuario);
        return usuarioRepository.editarUsuario(usuario);
    }



    public boolean excluirUsuario(Integer idUsuario){
        return this.usuarioRepository.excluirUsuario(idUsuario);
    }
}
