package com.projetofinal.ninjatask.repository;

import com.projetofinal.ninjatask.entity.Caderno;
import com.projetofinal.ninjatask.entity.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CadernoRepository {
    public Caderno criarCaderno(Caderno caderno) {
        Connection connection = null;
        try {
            //abrir conexao
            connection = ConexaoDB.getConnection();

            //executar operacao
            String sequence = "select seq_caderno.nextval proxval from DUAL";

            Statement statement = connection.createStatement();

            ResultSet retorno = statement.executeQuery(sequence);
            Integer idCaderno = -1;
            if (retorno.next()) {
                idCaderno = retorno.getInt("proxval");
            }
            String sql = "INSERT INTO CADERNO (ID_CADERNO, NOME_CADERNO, ID_USUARIO) VALUES (?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idCaderno);
            preparedStatement.setString(2, caderno.getNomeCaderno());
            preparedStatement.setInt(3, caderno.getUsuario().getIdUsuario());

            preparedStatement.executeUpdate();
            caderno.setIdCaderno(idCaderno);

            System.out.println("Caderno criado com sucesso repository");
            return caderno;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Caderno> listarCaderno() {
        List<Caderno> listaDeCaderno = new ArrayList<>();

        Connection connection = null;
        try {
            //abrir conexao
            connection = ConexaoDB.getConnection();

            String sql = "SELECT * FROM CADERNO";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Caderno caderno = new Caderno();
                Usuario usuario= new Usuario();
                caderno.setIdCaderno(resultSet.getInt("id_caderno"));
                caderno.setNomeCaderno(resultSet.getString("nome_caderno"));
//                caderno.setUsuario(resultSet.getInt("id_usuario"));
                usuario.getIdUsuario();
                caderno.setUsuario(usuario);
//                caderno.setUsuario(usuario.setIdUsuario());
                listaDeCaderno.add(caderno);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return listaDeCaderno;
        }
    }

    public boolean excluirCaderno(Integer idCaderno){
        Connection connection = null;
        try {
            connection = ConexaoDB.getConnection();

            String sql = "delete from caderno where id_caderno = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idCaderno);

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

    //listar cadernos por id usuario
    public List<Caderno> listarPorIdUsuario(Integer idUsuario){
        Connection connection = null;
        List<Caderno> listaDeCaderno = new ArrayList<>();
        try {
            //abrir conexao
            connection = ConexaoDB.getConnection();

            String sql = "SELECT c.*, u.ID_USUARIO , u.NOME_USUARIO, u.EMAIL_USUARIO\n" +
                    "\tFROM CADERNO c \n" +
                    "\tright JOIN USUARIO u ON (u.ID_USUARIO = c.ID_USUARIO)\n" +
                    "\tWHERE u.ID_USUARIO = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,idUsuario);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Caderno caderno = new Caderno();
                caderno.setIdCaderno(resultSet.getInt("id_caderno"));
                caderno.setNomeCaderno(resultSet.getString("nome_caderno"));
//                caderno.setIdUsuario(resultSet.getInt("id_usuario"));
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(resultSet.getInt("id_usuario"));
                usuario.setNomeUsuario(resultSet.getString("nome_usuario"));
                usuario.setEmailUsuario(resultSet.getString("email_usuario"));
                caderno.setUsuario(usuario);
                listaDeCaderno.add(caderno);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return listaDeCaderno;
        }
    }
}
