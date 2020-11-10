package com.lighttech.descobrindoomundo.Models;

import java.text.ParseException;
import java.util.Date;

import com.lighttech.descobrindoomundo.Enums.TipoUsuario;

public class Profissional {

    private int idProfissional;
    private String crm;

    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Profissional(){}

    @Override
    public String toString() {
        return "Profissional{" +
                "idProfissional=" + idProfissional +
                ", crm='" + crm + '\'' +
                '}';
    }
}
