package com.projetofinal.ninjatask.service;

import com.projetofinal.ninjatask.dto.ProjetoDTO;
import com.projetofinal.ninjatask.entity.ProjetoEntity;
import com.projetofinal.ninjatask.mapper.ProjetoMapper;
import com.projetofinal.ninjatask.repository.ProjetoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjetoService {
    private final ProjetoMapper projetoMapper;
    private final ProjetoRepository projetoRepository;

    public ProjetoDTO criarProjeto(ProjetoDTO projetoDTO){
        ProjetoEntity projetoEntity =projetoMapper.toEntity(projetoDTO);
        ProjetoEntity entitySalvo = projetoRepository.save(projetoEntity);
        ProjetoDTO projetoDTO1 = projetoMapper.toDTO(entitySalvo);
        return projetoDTO1;
    }

    public List<ProjetoDTO> listar(String nomeUsuario) {
        List<ProjetoEntity> listaEntidade = projetoRepository.findByNomeUsuario(nomeUsuario);
        List<ProjetoDTO> listaDTO = listaEntidade.stream().map(entity ->projetoMapper.toDTO(entity)).toList();
        return listaDTO;
    }

//    public
}
