package com.lighttech.descobrindoomundo.Models;

import com.lighttech.descobrindoomundo.Services.PalavraHttpService;
import com.lighttech.descobrindoomundo.Services.UsuarioHttpService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Palavra {

    public int id;
    public String nome;
    public int nivel;
    public Silaba silabas;
    public Componente componente;

    public Palavra(){}

    public Palavra(int id, String nome, Silaba silaba, Componente componente){
        this.setId(id);
        this.setNome(nome);
        this.setSilabas(silaba);
        this.setComponente(componente);
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

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Silaba getSilabas() {
        return silabas;
    }

    public void setSilabas(Silaba silabas) {
        this.silabas = silabas;
    }

    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    public Call<Palavra> buscar(int nivel)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PalavraHttpService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PalavraHttpService service = retrofit.create(PalavraHttpService.class);
        final Call<Palavra> requestPalavra = service.buscar(nivel);

        return requestPalavra;
    }

    @Override
    public String toString() {
        return "Palavra{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nivel=" + nivel +
                ", silaba=" + silabas +
                ", componente=" + componente +
                '}';
    }
}
