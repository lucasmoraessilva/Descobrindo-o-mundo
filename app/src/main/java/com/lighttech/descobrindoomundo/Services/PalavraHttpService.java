package com.lighttech.descobrindoomundo.Services;

import com.lighttech.descobrindoomundo.Models.Palavra;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PalavraHttpService {

    //public static final String BASE_URL = "http://192.168.0.73/descobrindomundo/api/";
    public static final String BASE_URL = "http://app-descobrindo-o-mundo.azurewebsites.net/api/";

    @GET("Palavra/{nivel}")
    Call<Palavra> buscar(@Path("nivel") int nivel);
}