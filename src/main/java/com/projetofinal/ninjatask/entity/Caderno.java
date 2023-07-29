package com.projetofinal.ninjatask.entity;

import com.projetofinal.ninjatask.entity.Usuario;
import lombok.*;

//@Getter
//@Setter
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
public class Caderno {
    private Integer idCaderno;
    private String nomeCaderno;
    private Integer idUsuario;
    private Usuario usuario;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdCaderno() {
        return idCaderno;
    }

    public void setIdCaderno(Integer idCaderno) {
        this.idCaderno = idCaderno;
    }

    public String getNomeCaderno() {
        return nomeCaderno;
    }

    public void setNomeCaderno(String nomeCaderno) {
        this.nomeCaderno = nomeCaderno;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Caderno{" +
                "idCaderno=" + idCaderno +
                ", nomeCaderno='" + nomeCaderno + '\'' +
                ", idUsuario=" + idUsuario +
                ", usuario=" + usuario+
                '}';
    }


}
