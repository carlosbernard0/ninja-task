package com.projetofinal.ninjatask.repository;

import com.projetofinal.ninjatask.entity.Caderno;
import com.projetofinal.ninjatask.entity.Tarefa;
import com.projetofinal.ninjatask.entity.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TarefaRepository {
    public Tarefa criarTarefa(Tarefa tarefa) {
        Connection connection = null;
        try {
            connection = ConexaoDB.getConnection();

            String sql = "INSERT INTO TAREFA (ID_TAREFA, NOME_TAREFA, STATUS_TAREFA)" +
                    "VALUES (?,?,?)";

            String sqlSequence = "select seq_tarefa.nextval proxval from DUAL";

            Statement statement = connection.createStatement();

            ResultSet retorno = statement.executeQuery(sqlSequence);

            Integer idTarefa = -1;
            if (retorno.next()) {
                idTarefa = retorno.getInt("proxval");
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, idTarefa);
            preparedStatement.setString(2, tarefa.getNome());
            preparedStatement.setString(3, tarefa.getStatus());
;
            preparedStatement.executeUpdate();
            tarefa.setIdTarefa(idTarefa);

            return tarefa;

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

    public List<Tarefa> listarTarefas() throws SQLException {
        List<Tarefa> listaDeTarefas = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConexaoDB.getConnection();


//            String sql = "select * from tarefa";
            String sql = "\t\n" +
                    "SELECT t.*, c.ID_CADERNO ,c.NOME_CADERNO ,c.ID_USUARIO\n" +
                    "    FROM TAREFA t \n" +
                    "   \tright JOIN CADERNO c ON (t.ID_CADERNO = c.ID_CADERNO)\n" +
                    "    WHERE c.ID_CADERNO  = 11";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

//            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                Tarefa tarefa = new Tarefa();
                Caderno caderno = new Caderno();
                Usuario usuario = new Usuario();

//                usuario.setIdUsuario(resultSet.getInt("id_usuario"));
//                usuario.setNomeUsuario(resultSet.getString("nome_usuario"));
//                usuario.setEmailUsuario(resultSet.getString("email_usuario"));

                caderno.setIdCaderno(resultSet.getInt("id_caderno"));
                caderno.setNomeCaderno(resultSet.getString("nome_caderno"));
                caderno.setUsuario(usuario);
//                usuario.setIdUsuario(resultSet.getInt("id_usuario"));
//                caderno.setUsuario(resultSet.getInt("id_usuario"));


                tarefa.setIdTarefa(resultSet.getInt("id_tarefa"));
                tarefa.setNome(resultSet.getString("nome_tarefa"));
                tarefa.setStatus(resultSet.getString("status_tarefa"));

                tarefa.setCaderno(caderno);
                listaDeTarefas.add(tarefa);
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
            return listaDeTarefas;
        }

    }

    public boolean editarTarefa(Tarefa tarefa){
        Connection connection = null;
        try {
            //abrir conexao
            connection = ConexaoDB.getConnection();

            //update
            String sql= "Update Tarefa set nome_tarefa = ?, status_tarefa = ? where id_tarefa = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(3, tarefa.getIdTarefa());
            preparedStatement.setString(1, tarefa.getNome());
            preparedStatement.setString(2, tarefa.getStatus());

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

    public boolean excluirTarefa(Integer idTarefa){
        Connection connection = null;
        try {
            connection = ConexaoDB.getConnection();

            String sql = "delete from tarefa where id_tarefa = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idTarefa);

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
            System.out.println("erro no banco");
        }

    }



}
