package com.lighttech.descobrindoomundo.Services;

import com.lighttech.descobrindoomundo.Models.Partida;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PartidaHttpService {

    public static final String BASE_URL = "http://192.168.0.73/descobrindomundo/api/";

    @POST("Partida")
    Call<Partida> Cadastrar(@Body Partida partida);

    @GET("Partida/{nickname}")
    Call<ArrayList<Partida>> Pesquisar(@Path("nickname") String nickname);
}