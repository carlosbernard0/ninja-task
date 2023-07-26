package com.projetofinal.ninjatask.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Date;


public class Usuario {

    @Schema(description = "codigo indentificador do usuario", example = "4")
    private Integer id_usuario;

    @Schema(description = "nome do usuario", example = "4")
    @NotEmpty
    @Size(min = 5, max = 20, message = "nome do cliente deve estar entre 5 e 20 caracteres")
    private String nome_usuario;

    @Schema(description = "email do usuario", example = "carlos@gmail.com")
    private String email_usuario;

    @Schema(description = "senha do usuario", example = "carlos123")
    @NotEmpty
    private String senha_usuario;

    @Schema(description = "data de alteração do cliente", example = "2023-07-26")
    @PastOrPresent
    private Date data_registro;

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getSenha_usuario() {
        return senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }

    public Date getData_registro() {
        return data_registro;
    }

    public void setData_registro(Date data_registro) {
        this.data_registro = data_registro;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id_usuario=" + id_usuario +
                ", nome_usuario='" + nome_usuario + '\'' +
                ", email_usuario='" + email_usuario + '\'' +
                ", senha_usuario='" + senha_usuario + '\'' +
                ", data_registro=" + data_registro +
                '}';
    }
}
