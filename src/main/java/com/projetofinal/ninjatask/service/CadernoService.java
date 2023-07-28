package com.projetofinal.ninjatask.service;

import com.projetofinal.ninjatask.entity.Caderno;
import com.projetofinal.ninjatask.repository.CadernoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadernoService {
    @Autowired
    private CadernoRepository cadernoRepository;

    public Caderno salvarCaderno(Caderno caderno){
        Caderno cadernoSalvo = cadernoRepository.criarCaderno(caderno);
        return cadernoSalvo;
    }

    public List<Caderno> listar(){
        List<Caderno> lista = cadernoRepository.listarCaderno();
        lista.stream().forEach(System.out::println);
        return lista;
    }

    public boolean excluirCaderno(Integer idCaderno){
        return this.cadernoRepository.excluirCaderno(idCaderno);
    }

    public List<Caderno> listarPorIdUsuario(Integer idUsuario){
        List<Caderno> list= cadernoRepository.listarPorIdUsuario(idUsuario);
        list.stream().forEach(System.out::println);
        return list;
    }
}
