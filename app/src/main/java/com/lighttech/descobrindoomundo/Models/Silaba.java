package com.lighttech.descobrindoomundo.Models;

public class Silaba {

    public int id;
    public int idPalavra;
    public String silabasCorretas;
    public String silabasIncorretas;

    public Silaba(){}

    public Silaba(int id, int idPalavra, String silabaCorreta, String silabaIncorreta){
        this.setId(id);
        this.setIdPalavra(idPalavra);
        this.setSilabasCorretas(silabaCorreta);
        this.setSilabasIncorretas(silabaIncorreta);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPalavra() {
        return idPalavra;
    }

    public void setIdPalavra(int idPalavra) {
        this.idPalavra = idPalavra;
    }

    public String getSilabasCorretas() {
        return silabasCorretas;
    }

    public void setSilabasCorretas(String silabasCorretas) {
        this.silabasCorretas = silabasCorretas;
    }

    public String getSilabasIncorretas() {
        return silabasIncorretas;
    }

    public void setSilabasIncorretas(String silabasIncorretas) {
        this.silabasIncorretas = silabasIncorretas;
    }
}
