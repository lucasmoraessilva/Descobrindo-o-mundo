package com.lighttech.descobrindoomundo.Models;

import java.util.Date;

import com.lighttech.descobrindoomundo.Enums.TipoUsuario;

public class Paciente extends Usuario {

    private Long idPaciente;
    private String nick;

    public Paciente(Long idUsuario, Long idPaciente, String nick, String nome, String email, String senha, Date dataNascimento, TipoUsuario tipo)
    {
        super(idUsuario, nome, email, senha, dataNascimento, tipo);
        this.idPaciente = idPaciente;
        this.nick = nick;
    }
}
