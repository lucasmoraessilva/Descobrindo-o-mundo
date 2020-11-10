package com.lighttech.descobrindoomundo.Models;

import java.text.ParseException;
import java.util.Date;

import com.lighttech.descobrindoomundo.Enums.TipoUsuario;

public class Paciente {

    private int id;
    private String nickname;

    public Paciente(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
