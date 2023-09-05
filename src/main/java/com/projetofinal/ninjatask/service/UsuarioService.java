package com.projetofinal.ninjatask.service;

import com.projetofinal.ninjatask.dto.AutenticacaoDTO;
import com.projetofinal.ninjatask.dto.PaginaDTO;
import com.projetofinal.ninjatask.dto.RelatorioUsuariosCadernosDTO;
import com.projetofinal.ninjatask.dto.UsuarioDTO;
import com.projetofinal.ninjatask.entity.UsuarioEntity;
import com.projetofinal.ninjatask.exceptions.BusinessException;
import com.projetofinal.ninjatask.mapper.UsuarioMapper;
import com.projetofinal.ninjatask.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;

    public UsuarioService(@Lazy UsuarioRepository usuarioRepository,
                          @Lazy AuthenticationManager authenticationManager, UsuarioMapper usuarioMapper){
        this.usuarioRepository= usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.usuarioMapper = usuarioMapper;
    }


    private final UsuarioMapper usuarioMapper;

    @Value("${jwt.validade.token}")
    private String validadeJWT;

    @Value("${jwt.secret}")
    private String secret;


    public String fazerLogin(AutenticacaoDTO autenticacaoDTO) throws BusinessException{
        UsernamePasswordAuthenticationToken dtoDoSpring = new UsernamePasswordAuthenticationToken(
                autenticacaoDTO.getEmailUsuario(),
                autenticacaoDTO.getSenhaUsuario()
        );
        try {
            Authentication autenticacao = authenticationManager.authenticate(dtoDoSpring);

            Object usuarioAutenticado = autenticacao.getPrincipal();
            UsuarioEntity usuarioEntity = (UsuarioEntity) usuarioAutenticado;

            List<String> nomeDosCargos = usuarioEntity.getCargos().stream()
                    .map(cargo -> cargo.getNome()).toList();

            Date dataAtual = new Date();
            Date dataExpiracao = new Date(dataAtual.getTime() + Long.parseLong(validadeJWT));

            //1 dia

            String jwtGerado =Jwts.builder()
                    .setIssuer("ninja-task")
                    .claim("CARGOS", nomeDosCargos)
                    .setSubject(usuarioEntity.getIdUsuario().toString())
                    .setIssuedAt(dataAtual)
                    .setExpiration(dataExpiracao)
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();

            return jwtGerado;
        }catch (AuthenticationException ex){
            throw new BusinessException("E-mail e Senha Inválidos");

        }
    }
    public void validarUsuario(UsuarioDTO usuario) throws BusinessException {
        if (!usuario.getEmailUsuario().contains("@")){
            throw new BusinessException("Precisa ter @");
        }
    }

    public UsuarioDTO salvarUsuario(UsuarioDTO usuario) throws BusinessException{
        validarUsuario(usuario);
        //converter dto para entity
        UsuarioEntity usuarioEntityConvertido = usuarioMapper.toEntity(usuario);
        UsuarioEntity usuarioEntitySalvo = usuarioRepository.save(usuarioEntityConvertido);
        //converter entity para dto
        UsuarioDTO usuarioRetornado = usuarioMapper.toDTO(usuarioEntitySalvo);
        return usuarioRetornado;

    }
    public boolean validarIdUsuario(Integer id) throws BusinessException {
        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(id);
        if (!usuarioOptional.isPresent()) {
            throw new BusinessException("ID inválido, usuário inexistente!");
        }
        return true;
    }
    public List<UsuarioDTO> listar() throws SQLException {
        List<UsuarioEntity> listaUsuario = usuarioRepository.findAll();
        List <UsuarioDTO> dtos = listaUsuario.stream().map(entity -> usuarioMapper.toDTO(entity)).toList();
        return dtos;
    }

    public void excluirUsuario(Integer idUsuario){
        usuarioRepository.deleteById(idUsuario);
    }

    public PaginaDTO<UsuarioDTO> listarPaginado(Integer paginaSolicitada, Integer tamanhoPorPagina){
        PageRequest pageRequest = PageRequest.of(paginaSolicitada, tamanhoPorPagina);
        Page<UsuarioEntity> paginaRecuperada = usuarioRepository.findAll(pageRequest);
        return new PaginaDTO<>(paginaRecuperada.getTotalElements(),
                paginaRecuperada.getTotalPages(),
                paginaSolicitada,
                tamanhoPorPagina,
                paginaRecuperada.getContent().stream().map(entity-> usuarioMapper.toDTO(entity)).toList());
    }

    public List<RelatorioUsuariosCadernosDTO> relatorio() {
        return usuarioRepository.buscarUsuariosCadernosETarefas();
    }
    public UsuarioEntity validarToken(String token) throws BusinessException {
        if(token == null){
            throw new BusinessException("Token Inexistente");
        }
        String tokenLimpo = token.replace("Bearer " ,"");
        Claims claims = Jwts.parser()
                .setSigningKey(secret) //utiliza a secret
                .parseClaimsJws(tokenLimpo) //decriptografa e valida o token...
                .getBody(); //recupera o payload

        String subject = claims.getSubject(); // id do usuario

//        String emailESenha[] = tokenLimpo.split("-"); //nao é mais utilizavel

        Optional <UsuarioEntity> usuarioEntityOptional = usuarioRepository.findById(Integer.parseInt(subject));
        return usuarioEntityOptional.orElseThrow(() -> new BusinessException("Usuário Inexistente"));
    }

    public Optional<UsuarioEntity> findByEmailUsuario(String emailUsuario){
        return usuarioRepository.findByEmailUsuario(emailUsuario    );
    }

}
