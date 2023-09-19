package com.projetofinal.ninjatask.service;

import com.projetofinal.ninjatask.dto.LogDTO;
import com.projetofinal.ninjatask.dto.ProjetoDTO;
import com.projetofinal.ninjatask.entity.LogEntity;
import com.projetofinal.ninjatask.entity.ProjetoEntity;
import com.projetofinal.ninjatask.entity.UsuarioEntity;
import com.projetofinal.ninjatask.mapper.LogMapper;
import com.projetofinal.ninjatask.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;
    private final LogMapper logMapper;

    UsuarioEntity usuarioEntity = new UsuarioEntity();
    public List<LogDTO> historico()throws SQLException {
        List<LogEntity> historico = logRepository.findAll();
        List <LogDTO> historicoDto = historico.stream().map(entity -> logMapper.toDto(entity)).toList();
        return historicoDto;
    }
    public void registrarLogin(UsuarioEntity usuarioEntity){
        LogEntity logEntity = new LogEntity();
        logEntity.setIdUsuario(usuarioEntity.getIdUsuario());
        logEntity.setNomeUsuario(usuarioEntity.getNomeUsuario());
        Date dataN = new Date();
        logEntity.setDataDeLogin(dataN);
        logRepository.save(logEntity);
    }
}
