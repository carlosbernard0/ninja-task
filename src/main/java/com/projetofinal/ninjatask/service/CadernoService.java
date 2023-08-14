package com.projetofinal.ninjatask.service;

import com.projetofinal.ninjatask.controller.CadernoController;
import com.projetofinal.ninjatask.dto.CadernoDto;
import com.projetofinal.ninjatask.entity.CadernoEntity;
import com.projetofinal.ninjatask.mapper.CadernoMapper;
import com.projetofinal.ninjatask.repository.CadernoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CadernoService {
    private final CadernoRepository cadernoRepository;
    private final CadernoMapper cadernoMapper;

    public CadernoDto salvarCaderno(@RequestBody CadernoDto dto){
        CadernoEntity entity = cadernoMapper.toEntity(dto);
        CadernoEntity salvo = cadernoRepository.save(entity);
        CadernoDto dtoSalvo = cadernoMapper.toDto(salvo);

        return dtoSalvo;
    }

    public List<CadernoDto> listar(){
        List<CadernoEntity> listaCadernos = cadernoRepository.findAll();
        List<CadernoDto> listaDtos = listaCadernos.stream()
                .map(entity -> cadernoMapper.toDto(entity))
                .toList();
        return listaDtos;
    }

    public void excluirCaderno(Integer id){
        cadernoRepository.deleteById(id);
    }

//    public List<CadernoDto> listarPorIdUsuario(Integer idUsuario){
//        List<CadernoDto> listaDtos = this.cadernoRepository.listarPorIdUsuario(idUsuario).stream()
//                .map(entity -> cadernoMapper.toDto(entity))
//                .toList();
//        return listaDtos;
//    }
}
