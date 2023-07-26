package com.projetofinal.ninjatask.repository;

import com.projetofinal.ninjatask.entity.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class UsuarioRepository {
    public Usuario cadastrarUsuario(Usuario usuario) {
        Connection connection = null;
        try {
            connection = ConexaoDB.getConnection();

            String sql = "INSERT INTO USUARIO (ID_USUARIO, NOME_USUARIO, EMAIL_USUARIO,SENHA_USUARIO, DATA_REGISTRO)" +
                    "VALUES (?,?,?,?,?)";

            String sqlSequence = "select seq_usuario.nextval proxval from DUAL";

            Statement statement = connection.createStatement();

            ResultSet retorno = statement.executeQuery(sqlSequence);

            Integer idUsuario = -1;
            if (retorno.next()) {
                idUsuario = retorno.getInt("proxval");
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, idUsuario);
            preparedStatement.setString(2, usuario.getNome_usuario());
            preparedStatement.setString(3, usuario.getEmail_usuario());
            preparedStatement.setString(4, usuario.getSenha_usuario());
            preparedStatement.setDate(5, new Date(usuario.getData_registro().getTime()));

            preparedStatement.executeUpdate();
            usuario.setId_usuario(idUsuario);

//            int resposta = preparedStatement.executeUpdate();
//            System.out.println("salvarUsuarioDB.resposta: " + resposta);


            System.out.println("Usuario cadastrado repository");
            return usuario;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public List<Usuario> listarUsuario() throws SQLException {
        List<Usuario> listaDeUsuarios = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConexaoDB.getConnection();


            String sql = "select * from usuario";

            //        Statement statement = connection.createStatement();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setId_usuario(resultSet.getInt("id_usuario"));
                usuario.setNome_usuario(resultSet.getString("nome_usuario"));
                usuario.setEmail_usuario(resultSet.getString("email_usuario"));
                usuario.setSenha_usuario(resultSet.getString("senha_usuario"));
                usuario.setData_registro(resultSet.getDate("data_registro"));

                listaDeUsuarios.add(usuario);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return listaDeUsuarios;
        }

    }

    public boolean editarUsuario(Usuario usuario){
        Connection connection = null;
        try {
            //abrir conexao
            connection = ConexaoDB.getConnection();

            //update
            String sql= "Update Usuario set nome_usuario = ?, email_usuario = ?, senha_usuario = ?, data_registro = ? where id_usuario = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, usuario.getNome_usuario());
            preparedStatement.setString(2, usuario.getEmail_usuario());
            preparedStatement.setString(3, usuario.getSenha_usuario());
            preparedStatement.setDate(4, new Date(usuario.getData_registro().getTime()));
            preparedStatement.setInt(5,usuario.getId_usuario());

            //executar
            preparedStatement.executeUpdate();
            System.out.println("-- Atualizando o usuário...");
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean excluirUsuario(Integer idUsuario){
        Connection connection = null;
        try {
            connection = ConexaoDB.getConnection();

            String sql = "delete from usuario where id_usuario = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idUsuario);

            int resultadoExcluir = preparedStatement.executeUpdate();
            return resultadoExcluir > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
