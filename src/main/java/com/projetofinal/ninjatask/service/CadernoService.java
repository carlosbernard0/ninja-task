package com.projetofinal.ninjatask.service;

import com.projetofinal.ninjatask.dto.CadernoDTO;
import com.projetofinal.ninjatask.entity.CadernoEntity;
import com.projetofinal.ninjatask.exceptions.BusinessException;
import com.projetofinal.ninjatask.mapper.CadernoMapper;
import com.projetofinal.ninjatask.repository.CadernoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CadernoService {
    private final CadernoRepository cadernoRepository;
    private final CadernoMapper cadernoMapper;
    private final UsuarioService usuarioService;

    public CadernoDTO salvarCaderno(@RequestBody CadernoDTO dto) throws BusinessException {
        usuarioService.validarIdUsuario(dto.getUsuario().getIdUsuario());

        CadernoEntity entity = cadernoMapper.toEntity(dto);
        CadernoEntity salvo = cadernoRepository.save(entity);
        CadernoDTO dtoSalvo = cadernoMapper.toDto(salvo);

        return dtoSalvo;
    }

    public boolean validarIdCaderno(Integer id) throws BusinessException {
        Optional<CadernoEntity> cadernoOptional = cadernoRepository.findById(id);
        if (!cadernoOptional.isPresent()) {
            throw new BusinessException("ID inválido, caderno inexistente!");
        }
        return true;
    }
    public List<CadernoDTO> listar(){
        List<CadernoEntity> listaCadernos = cadernoRepository.findAll();
        List<CadernoDTO> listaDtos = listaCadernos.stream()
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
