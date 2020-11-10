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
    private int IdPartida;
    private int IdJogo;
    private int IdPaciente;
    private int IdPalavra;
    private String Data;
    private String Duracao;
    private String Status;
    private int QtdErros;
    private int QtdAcertos;

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
        return IdPartida;
    }

    public void setIdPartida(int idPartida) {
        IdPartida = idPartida;
    }

    public int getIdJogo() {
        return IdJogo;
    }

    public void setIdJogo(int idJogo) {
        IdJogo = idJogo;
    }

    public int getIdPaciente() {
        return IdPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        IdPaciente = idPaciente;
    }

    public int getIdPalavra() {
        return IdPalavra;
    }

    public void setIdPalavra(int idPalavra) {
        IdPalavra = idPalavra;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        /*DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Data = LocalDate.parse(data, formatoData);*/
        Data = data;
    }

    public String getDuracao() {
        return Duracao;
    }

    public void setDuracao(String duracao) {
        /*DateTimeFormatter formatoDuracao = DateTimeFormatter.ofPattern("HH:mm:ss");
        Duracao = LocalTime.parse(duracao,formatoDuracao);*/
        Duracao = duracao;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getQtdErros() {
        return QtdErros;
    }

    public void setQtdErros(int qtdErros) {
        QtdErros = qtdErros;
    }

    public int getQtdAcertos() {
        return QtdAcertos;
    }

    public void setQtdAcertos(int qtdAcertos) {
        QtdAcertos = qtdAcertos;
    }

    public ArrayList<Partida> Pesquisar (String nickname){
        /*
         * Pesquisar partida na API usando nickname
         */
        return new ArrayList<Partida>();
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

    @Override
    public String toString() {
        return "Partida{" +
                "IdPartida=" + IdPartida +
                ", IdJogo=" + IdJogo +
                ", IdPaciente=" + IdPaciente +
                ", IdPalavra=" + IdPalavra +
                ", Data=" + getData() +
                ", Duracao=" + getDuracao() +
                ", Status='" + Status + '\'' +
                ", QtdErros=" + QtdErros +
                ", QtdAcertos=" + QtdAcertos +
                '}';
    }


}