package com.projetofinal.ninjatask.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Entity(name = "Usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity implements UserDetails {
    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gerador_usuario")
    @SequenceGenerator(name = "gerador_usuario", sequenceName = "seq_usuario", allocationSize = 1)
    private Integer idUsuario;
    @Column(name = "nome_usuario")
    private String nomeUsuario;
    @Column(name = "email_usuario")
    private String emailUsuario;
    @Column(name = "senha_usuario")
    private String senhaUsuario;
    @Column(name = "data_registro")
    private Date dataRegistro;
    @Column(name = "ativo")
    private Boolean ativo;


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    private Set<CadernoEntity> cadernos;


    @ManyToMany
    @JoinTable(name = "Usuario_Cargo",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_cargo"))
    public Set<CargoEntity> cargos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return cargos;
    }

    @Override
    public String getPassword() {
        return senhaUsuario;
    }

    @Override
    public String getUsername() {
        return emailUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.ativo;
    }

    public void excluir() {
        this.ativo = false;
    }
}
