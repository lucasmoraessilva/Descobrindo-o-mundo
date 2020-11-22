package com.lighttech.descobrindoomundo.Models;

import java.text.ParseException;
import java.util.Date;

import com.lighttech.descobrindoomundo.Enums.TipoUsuario;

public class Paciente {

    private int Id;
    private int IdUsuario;
    private String Nickname;

    public Paciente(){}

    public Paciente(int id, String nickname, String nome, String email, String data, String senha) {
        this.Id = id;
        this.Nickname = nickname;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        this.Nickname = nickname;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "Id=" + Id +
                ", IdUsuario=" + IdUsuario +
                ", Nickname='" + Nickname + '\'' +
                '}';
    }
}
