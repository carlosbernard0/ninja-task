package com.projetofinal.ninjatask.service;

import com.projetofinal.ninjatask.dto.CadernoDto;
import com.projetofinal.ninjatask.entity.CadernoEntity;
import com.projetofinal.ninjatask.mapper.CadernoMapper;
import com.projetofinal.ninjatask.repository.CadernoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CadernoService {
    private final CadernoRepository cadernoRepository;
    private final CadernoMapper cadernoMapper;

    public CadernoDto salvarCaderno(CadernoDto cadernoDto){
        CadernoEntity cadernoEntityConvertido = cadernoMapper.converterParaEntity(cadernoDto);

        CadernoEntity cadernoEntitySalvo = cadernoRepository.criarCaderno(cadernoEntityConvertido);

        CadernoDto cadernoRetornado = cadernoMapper.converterParaDto(cadernoEntitySalvo);

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
