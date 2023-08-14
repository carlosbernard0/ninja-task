package com.projetofinal.ninjatask.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity(name = "Usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UsuarioEntity {
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    private Set<CadernoEntity> cadernos;
}
