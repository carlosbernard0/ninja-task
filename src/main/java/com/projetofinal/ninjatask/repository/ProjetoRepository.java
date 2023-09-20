package com.projetofinal.ninjatask.repository;

import com.projetofinal.ninjatask.entity.ProjetoEntity;
import com.projetofinal.ninjatask.entity.ProjetosPorTipoEntity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepository extends MongoRepository<ProjetoEntity, String> {
    List<ProjetoEntity> findByNomeUsuarioContainingIgnoreCase(String nomeUsuario);


    @Aggregation(pipeline = {
            "{$match: {nomeProjeto: /.*?0.*/i}}",
            "{$group: {'_id': '$tipoProjeto', contagem: {$sum: 1}}}",
            "{$project: {'tipo': '$_id', 'quantidade': '$contagem'}}",
            "{$sort: {'tipo': 1}}"
    })
    List<ProjetosPorTipoEntity> findProjetosPorTipoAgregados(String nome);
}
