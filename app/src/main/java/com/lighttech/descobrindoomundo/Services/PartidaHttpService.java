package com.lighttech.descobrindoomundo.Services;

import com.lighttech.descobrindoomundo.Models.Partida;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PartidaHttpService {

    public static final String BASE_URL = "http://192.168.0.73/descobrindomundo/api/";

    @POST("Partida")
    Call<Partida> Cadastrar(@Body Partida partida);
}