package com.projetofinal.ninjatask.service;

import com.projetofinal.ninjatask.dto.ProjetoDTO;
import com.projetofinal.ninjatask.dto.ProjetosPorTipoDTO;
import com.projetofinal.ninjatask.entity.ProjetoEntity;
import com.projetofinal.ninjatask.entity.ProjetosPorTipoEntity;
import com.projetofinal.ninjatask.mapper.ProjetoMapper;
import com.projetofinal.ninjatask.mapper.ProjetosPorTipoMapper;
import com.projetofinal.ninjatask.repository.ProjetoRepository;
import com.projetofinal.ninjatask.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjetoService {
    private final ProjetoMapper projetoMapper;
    private final ProjetoRepository projetoRepository;
    private final UsuarioService usuarioService;
    private final ProjetosPorTipoMapper projetosPorTipoMapper;



    public ProjetoDTO criarProjeto(ProjetoDTO projeto){
        String nomeU = usuarioService.findByIdUsuario();
        ProjetoEntity projetoEntity =projetoMapper.toEntity(projeto);
        projetoEntity.setNomeUsuario(nomeU);
        ProjetoEntity entitySalvo = projetoRepository.save(projetoEntity);
        ProjetoDTO  projetoDTO = projetoMapper.toDTO(entitySalvo);
        return projetoDTO;
    }


    public List<ProjetoDTO> listar(String nomeUsuario) {
        List<ProjetoEntity> listaEntidade = projetoRepository.findByNomeUsuarioContainingIgnoreCase(nomeUsuario);
        List<ProjetoDTO> listaDTO = listaEntidade.stream().map(entity ->projetoMapper.toDTO(entity)).toList();
        return listaDTO;
    }
    public void excluir(String id) {
        projetoRepository.deleteById(id);
        
    }
    public List<ProjetosPorTipoDTO> listarPorTipoAgregado() {
        List<ProjetosPorTipoEntity> listaEntidade = projetoRepository.findProjetosPorTipoAgregados();
        List<ProjetosPorTipoDTO> listaDTO = listaEntidade.stream().map(entity ->projetosPorTipoMapper.toDTO(entity)).toList();
        return listaDTO;
    }

}
