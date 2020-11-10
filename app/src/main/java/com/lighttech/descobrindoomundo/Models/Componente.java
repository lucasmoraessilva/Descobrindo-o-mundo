package com.lighttech.descobrindoomundo.Models;

public class Componente {

    public int id;
    public int idPalavra;
    public String nome;
    public int localizacao;
    public int tipo;
    public String descricao;

    public Componente(){}

    public Componente(int id, String nome, String localizacao){
        this.setId(id);
        this.setNome(nome);
        this.setLocalizacao(localizacao);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdPalavra() {
        return idPalavra;
    }

    public void setIdPalavra(int idPalavra) {
        this.idPalavra = idPalavra;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = Integer.valueOf(localizacao);
    }

}