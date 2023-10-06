package com.projetofinal.ninjatask.service;

import com.projetofinal.ninjatask.dto.*;
import com.projetofinal.ninjatask.entity.CargoEntity;
import com.projetofinal.ninjatask.entity.UsuarioEntity;
import com.projetofinal.ninjatask.exceptions.BusinessException;
import com.projetofinal.ninjatask.mapper.UsuarioMapper;
import com.projetofinal.ninjatask.repository.UsuarioCargoRepository;
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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final LogService logService;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioCargoRepository usuarioCargoRepository;
    private final AuthenticationManager authenticationManager;
    private final UsuarioMapper usuarioMapper;

    public Integer idLogado;

    public UsuarioService(LogService logService, @Lazy UsuarioRepository usuarioRepository,
                          UsuarioCargoRepository usuarioCargoRepository, @Lazy AuthenticationManager authenticationManager, UsuarioMapper usuarioMapper){
        this.logService = logService;
        this.usuarioRepository= usuarioRepository;
        this.usuarioCargoRepository = usuarioCargoRepository;
        this.authenticationManager = authenticationManager;
        this.usuarioMapper = usuarioMapper;
    }



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

            //Inserir nome no Projeto
              idLogado = usuarioEntity.getIdUsuario();
            //Inserir Log de usuários
              logService.registrarLogin(usuarioEntity);
            //-------------------------------------------
            return jwtGerado;
        }catch (AuthenticationException ex){
            throw new BusinessException("E-mail e Senha Inválidos");

        }
    }

    public UsernamePasswordAuthenticationToken validarToken(String token){
        if(token == null){
            return null;
        }
        String tokenLimpo = token.replace("Bearer " ,"");
        Claims claims = Jwts.parser()
                .setSigningKey(secret) //utiliza a secret
                .parseClaimsJws(tokenLimpo) //decriptografa e valida o token...
                .getBody(); //recupera o payload

        String idUsuario = claims.getSubject(); // id do usuario
        List<String> cargos = claims.get("CARGOS", List.class);

        List<SimpleGrantedAuthority> listaDeCargos = cargos.stream()
                .map(cargoStr -> new SimpleGrantedAuthority(cargoStr))
                .toList();

        UsernamePasswordAuthenticationToken tokenSpring = new UsernamePasswordAuthenticationToken(idUsuario, null,
                listaDeCargos);

        return tokenSpring;
    }

    public void validarUsuario(UsuarioDTO usuario) throws BusinessException {
        if (!usuario.getEmailUsuario().contains("@")){
            throw new BusinessException("Precisa ter @");
        }
    }


    public boolean validarEmailExistente(String Email) throws BusinessException {
        Optional<UsuarioEntity> emailExistente = usuarioRepository.findByEmailUsuario(Email);
        if (emailExistente.isPresent()) {
            throw new BusinessException("Email já cadastrado");
        }
        return true;
    }

    public UsuarioDTO salvarUsuario(UsuarioDTO usuario) throws BusinessException{
        validarUsuario(usuario);
        //converter dto para entity
        UsuarioEntity usuarioEntityConvertido = usuarioMapper.toEntity(usuario);
        // Verificar Existência E-mail
        validarEmailExistente(usuario.getEmailUsuario());
        //Converter Senha
        String senha = usuarioEntityConvertido.getSenhaUsuario();
        String senhaCriptografada = converterSenha(senha);
        usuarioEntityConvertido.setSenhaUsuario(senhaCriptografada);
        UsuarioEntity usuarioEntitySalvo = usuarioRepository.save(usuarioEntityConvertido);

        // Inicialize a lista de cargos se for nula
        if (usuarioEntitySalvo.getCargos() == null) {
            usuarioEntitySalvo.setCargos(new HashSet<>());
        }

        // Criar uma instância do CargoEntity com o ID do cargo igual a 3
        CargoEntity cargo = new CargoEntity();
        cargo.setIdCargo(3); // Certifique-se de que a entidade CargoEntity tenha um setter para o ID do cargo

        // Adicionar o cargo à lista de cargos do usuário
        usuarioEntitySalvo.getCargos().add(cargo);

        // Atualizar o usuário para salvar a relação com o cargo
        usuarioEntitySalvo = usuarioRepository.save(usuarioEntitySalvo);

        // Converter Entity para DTO
        UsuarioDTO usuarioRetornado = usuarioMapper.toDTO(usuarioEntitySalvo);
        return usuarioRetornado;
    }

//    public UsuarioCargoEntity cargoAutomatico(UsuarioEntity usuarioEntitySalvo){
//        UsuarioCargoEntity usuarioCargoEntity = null;
//        usuarioCargoEntity.setIdUsuario(usuarioEntitySalvo.getIdUsuario());
//        usuarioCargoEntity.setIdCargo(String.valueOf(3));
//        usuarioCargoRepository.save(usuarioCargoEntity);
//        return usuarioCargoEntity;
//    }

    public UsuarioDTO editarUsuario(UsuarioDTO usuario) throws BusinessException{
        validarUsuario(usuario);
        //converter dto para entity
        UsuarioEntity usuarioEntityConvertido = usuarioMapper.toEntity(usuario);
        //Converter Senha
        String senha = usuarioEntityConvertido.getSenhaUsuario();
        String senhaCriptografada = converterSenha(senha);
        usuarioEntityConvertido.setSenhaUsuario(senhaCriptografada);
        UsuarioEntity usuarioEntitySalvo = usuarioRepository.save(usuarioEntityConvertido);
        //converter entity para dto
        UsuarioDTO usuarioRetornado = usuarioMapper.toDTO(usuarioEntitySalvo);
        return usuarioRetornado;
    }

    public String converterSenha(String senha){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String senhaCriptografada = bCryptPasswordEncoder.encode(senha);
        return senhaCriptografada;
    }
    public boolean validarIdUsuario(Integer id) throws BusinessException {
        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(id);
        if (!usuarioOptional.isPresent()) {
            throw new BusinessException("ID inválido, usuário inexistente!");
        }
        return true;
    }
    public UsuarioDTO validarIdLogado() throws BusinessException {
        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(idLogado);
        if (!usuarioOptional.isPresent()) {
            throw new BusinessException("ID inválido, usuário inexistente!");
        }
        UsuarioEntity usuarioEntity = usuarioOptional.get();
        UsuarioDTO usuarioDTO = usuarioMapper.toDTO(usuarioEntity);
        return usuarioDTO;
    }

    public Integer retornarIdLogado(){
        return idLogado;
    }
    public List<UsuarioDTOSemSenha> listar() throws SQLException {
        List<UsuarioEntity> listaUsuario = usuarioRepository.findAll();
        List <UsuarioDTOSemSenha> dtos = listaUsuario.stream().map(entity -> usuarioMapper.toDTOSemSenha(entity)).toList();
        return dtos;
    }

    public void excluirUsuario(Integer idUsuario){
        usuarioRepository.deleteById(idUsuario);
    }

    public void destivarUsuario(Integer idUsuario){
        UsuarioEntity usuario = usuarioRepository.getReferenceById(idUsuario);
        usuario.setAtivo(false);
        usuarioRepository.save(usuario);

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

    public List<RelatorioUsuariosTarefasDTO> relatorio() {
        return usuarioRepository.buscarUsuariosETarefas();
    }

    public Optional<UsuarioEntity> findByEmailUsuario(String emailUsuario){
        return usuarioRepository.findByEmailUsuario(emailUsuario);
    }

    public String findByIdUsuario(){
        Optional<UsuarioEntity> usuario =usuarioRepository.findByIdUsuario(idLogado);
        return usuario.get().getNomeUsuario();
    }


    public Integer recuperarIdUsuarioLogado(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object idUsuario = authentication.getPrincipal();
        String idUsuarioString = (String) idUsuario;
        return Integer.parseInt(idUsuarioString);
    }

    public UsuarioDTO recuperarUsuarioLogado() throws BusinessException {
        Integer idUsuarioLogado =recuperarIdUsuarioLogado();
        UsuarioEntity idUsuarioEntity = usuarioRepository.findById(idUsuarioLogado).orElseThrow(() -> new BusinessException("Usuario não encontrado!"));
        UsuarioDTO idUsuarioDTOLogado = usuarioMapper.toDTO(idUsuarioEntity);
        return idUsuarioDTOLogado;
    }

}
