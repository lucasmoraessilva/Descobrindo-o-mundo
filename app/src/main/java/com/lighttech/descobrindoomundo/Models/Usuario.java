package com.lighttech.descobrindoomundo.Models;

import java.util.Date;

import com.lighttech.descobrindoomundo.DAL.UsuarioDAL;
import com.lighttech.descobrindoomundo.Enums.TipoUsuario;

public class Usuario {

    protected Long idUsuario;
    protected String nome;
    protected String email;
    protected String senha;
    protected Date dataNascimento;
    protected TipoUsuario tipo;

    public Usuario(Long id, String nome, String email, String senha, Date dataNascimento, TipoUsuario tipo)
    {
        this.setIdUsuario(id);
        this.setNome(nome);
        this.setEmail(email);
        this.setSenha(senha);
        this.setDataNascimento(dataNascimento);
        this.setTipo(tipo);
    }

    public Usuario(String email, String senha)
    {
        this.setEmail(email);
        this.setSenha(senha);
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long id) {
        this.idUsuario = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public boolean Login(String email, String senha)
    {
        /*
        * Aqui tentei fazer uma implementação de login vinculado com o login do usuárioDAL.
        * Acho que nessa parte estou meio perdido.
        */
        UsuarioDAL usuarioDAL = new UsuarioDAL(this);
        return true;
    }
}
