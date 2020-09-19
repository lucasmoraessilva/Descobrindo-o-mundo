package com.lighttech.descobrindoomundo.Models;

import java.util.Date;

import com.lighttech.descobrindoomundo.Enums.TipoUsuario;

public class Profissional extends Usuario {
    private Long idProfissional;
    private String crm;

    public Profissional(Long idUsuario, Long idProfissional, String crm, String nome, String email, String senha, Date dataNascimento, TipoUsuario tipo)
    {
        super(idUsuario, nome, email, senha, dataNascimento, tipo);
        this.idProfissional = idProfissional;
        this.crm = crm;
    }
}
