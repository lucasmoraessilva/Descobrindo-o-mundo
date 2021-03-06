package com.lighttech.descobrindoomundo.Services;

import com.lighttech.descobrindoomundo.Models.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UsuarioHttpService {

    //public static final String BASE_URL = "http://192.168.0.73/descobrindomundo/api/";
    public static final String BASE_URL = "http://app-descobrindo-o-mundo.azurewebsites.net/api/";


    @GET("Usuario/Login")
    Call<Usuario> Login(@Query("email") String email, @Query("senha") String senha);

    @POST("Usuario")
    Call<Usuario> Cadastrar(@Body Usuario usuario);

    @PUT("Usuario")
    Call<Usuario> Atualizar(@Body Usuario usuario);
}
