package com.lighttech.descobrindoomundo.Models;

import com.lighttech.descobrindoomundo.Services.PartidaHttpService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Partida {
    private int idPartida;
    private int idJogo;
    private int idPaciente;
    private int idPalavra;
    private String data;
    private String duracao;
    private String status;
    private int qtdErros;
    private int qtdAcertos;

    public Partida(){}

    public Partida(int id, int idJogo, int idPaciente, int idPalavra, String data, String duracao, String status, int qtdErros, int qtdAcertos){
        this.setIdPartida(id);
        this.setIdJogo(idJogo);
        this.setIdPaciente(idPaciente);
        this.setIdPalavra(idPalavra);
        this.setData(data);
        this.setDuracao(duracao);
        this.setStatus(status);
        this.setQtdErros(qtdErros);
        this.setQtdAcertos(qtdAcertos);
    }

    public Partida(int idJogo, int idPaciente, int idPalavra, String data, String duracao, String status, int qtdErros, int qtdAcertos){
        this.setIdJogo(idJogo);
        this.setIdPaciente(idPaciente);
        this.setIdPalavra(idPalavra);
        this.setData(data);
        this.setDuracao(duracao);
        this.setStatus(status);
        this.setQtdErros(qtdErros);
        this.setQtdAcertos(qtdAcertos);
    }

    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        idPartida = idPartida;
    }

    public int getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        idJogo = idJogo;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        idPaciente = idPaciente;
    }

    public int getIdPalavra() {
        return idPalavra;
    }

    public void setIdPalavra(int idPalavra) {
        idPalavra = idPalavra;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        data = data;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        duracao = duracao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        status = status;
    }

    public int getQtdErros() {
        return qtdErros;
    }

    public void setQtdErros(int qtdErros) {
        qtdErros = qtdErros;
    }

    public int getQtdAcertos() {
        return qtdAcertos;
    }

    public void setQtdAcertos(int qtdAcertos) {
        qtdAcertos = qtdAcertos;
    }

    public Call<Partida> Cadastrar(Partida partida)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PartidaHttpService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PartidaHttpService service = retrofit.create(PartidaHttpService.class);
        final Call<Partida> requestPartida = service.Cadastrar(partida);

        return requestPartida;
    }

    public Call<ArrayList<Partida>> Pesquisar(String nickname){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PartidaHttpService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PartidaHttpService service = retrofit.create(PartidaHttpService.class);
        final Call<ArrayList<Partida>> requestPartida = service.Pesquisar(nickname);

        return requestPartida;
    }

    public String listarDadosResposta(){
        return "Data: " + getData() +
                "\nDuracao: " + getDuracao() +
                "\nStatus: '" + status + '\'' +
                "\nQuantidade de erros: " + qtdErros +
                "\nQuantidade de acertos: " + qtdAcertos
                ;
    }

    @Override
    public String toString() {
        return "Partida{" +
                "IdPartida=" + idPartida +
                ", IdJogo=" + idJogo +
                ", IdPaciente=" + idPaciente +
                ", IdPalavra=" + idPalavra +
                ", Data=" + getData() +
                ", Duracao=" + getDuracao() +
                ", Status='" + status + '\'' +
                ", QtdErros=" + qtdErros +
                ", QtdAcertos=" + qtdAcertos +
                '}';
    }


}