package com.projetofinal.ninjatask.service;

import com.projetofinal.ninjatask.entity.Tarefa;
import com.projetofinal.ninjatask.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;
    public TarefaService(){
        tarefaRepository = new TarefaRepository();
    }

    public Tarefa salvarTarefa(Tarefa tarefa){
        return tarefaRepository.criarTarefa(tarefa);
//        return tarefa;
    }

    public List<Tarefa> listarTarefas()throws SQLException {
        List<Tarefa> lista = tarefaRepository.listarTarefas();
        lista.stream().forEach(System.out::println);
        return lista;
    }

    public boolean editarTarefa(Tarefa tarefa) throws Exception {
        return tarefaRepository.editarTarefa(tarefa);
    }

    public boolean excluirTarefa(Integer idTarefa){
        return this.tarefaRepository.excluirTarefa(idTarefa);
    }


}
