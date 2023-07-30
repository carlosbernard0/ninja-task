package com.projetofinal.ninjatask.service;

import com.projetofinal.ninjatask.dto.CadernoDto;
import com.projetofinal.ninjatask.entity.Caderno;
import com.projetofinal.ninjatask.entity.Usuario;
import com.projetofinal.ninjatask.mapper.CadernoMapper;
import com.projetofinal.ninjatask.repository.CadernoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CadernoService {
    private final CadernoRepository cadernoRepository;
    private final CadernoMapper cadernoMapper;

    public CadernoDto salvarCaderno(CadernoDto cadernoDto){
        Caderno cadernoConvertido = cadernoMapper.converterParaEntity(cadernoDto);

        Caderno cadernoSalvo = cadernoRepository.criarCaderno(cadernoConvertido);

        CadernoDto cadernoRetornado = cadernoMapper.converterParaDto(cadernoSalvo);

        return cadernoRetornado;
    }

    public List<CadernoDto> listar(){
        List<CadernoDto> listaDtos = this.cadernoRepository.listarCaderno().stream()
                .map(entity -> cadernoMapper.converterParaDto(entity))
                .toList();
        return listaDtos;
    }

    public boolean excluirCaderno(Integer idCaderno){
        return this.cadernoRepository.excluirCaderno(idCaderno);
    }

    public List<CadernoDto> listarPorIdUsuario(Integer idUsuario){
        List<CadernoDto> listaDtos = this.cadernoRepository.listarPorIdUsuario(idUsuario).stream()
                .map(entity -> cadernoMapper.converterParaDto(entity))
                .toList();
        return listaDtos;
    }
}
