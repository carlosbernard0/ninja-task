package com.projetofinal.ninjatask.entity;

public class Tarefa {
    private String nome, status;
    private Integer idTarefa;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(Integer idTarefa) {
        this.idTarefa = idTarefa;
    }


    @Override
    public String toString() {
        return "Tarefa{" +
                "nome='" + nome + '\'' +
                ", status='" + status + '\'' +
                ", idTarefa=" + idTarefa + '}';
    }

}
