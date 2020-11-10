package com.lighttech.descobrindoomundo.Models;

import java.time.LocalDate;
import com.lighttech.descobrindoomundo.Services.UsuarioHttpService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Usuario {

    private int id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String dtNascimento;
    private int tipo;
    private Paciente paciente = new Paciente();
    private Profissional profissional = new Profissional();

    public Usuario(){

    }

    public Usuario(String nome, String sobrenome, String email, String senha, String dataNascimento, int tipo, String nickname) {
        this.setNome(nome);
        this.setSobrenome(sobrenome);
        this.setEmail(email);
        this.setSenha(senha);
        this.setDtNascimento(dataNascimento);
        this.setTipo(tipo);
        this.paciente.setNickname(nickname);
    }

    public Usuario(String nome, String sobrenome, String email, String senha, String dataNascimento, int tipo, int crm) {
        this.setIdUsuario(id);
        this.setNome(nome);
        this.setSobrenome(sobrenome);
        this.setEmail(email);
        this.setSenha(senha);
        this.setDtNascimento(dataNascimento);
        this.setTipo(tipo);
        this.profissional.setCrm(String.valueOf(crm));
    }

    public Usuario(String email, String senha)
    {
        this.setEmail(email);
        this.setSenha(senha);
    }

    public int getIdUsuario() {
        return id;
    }

    public void setIdUsuario(int idUsuario) {
        this.id = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Call<Usuario> Login(String email, String senha){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UsuarioHttpService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsuarioHttpService service = retrofit.create(UsuarioHttpService.class);
        final Call<Usuario> requestUsuario = service.Login(email,senha);

        return requestUsuario;
    }

    public Call<Usuario> Cadastrar(Usuario usuario)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UsuarioHttpService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsuarioHttpService service = retrofit.create(UsuarioHttpService.class);
        final Call<Usuario> requestUsuario = service.Cadastrar(usuario);

        return requestUsuario;
    }

    public void RecuperarSenha(String email)
    {
        /*TODO
        * Requisição HTTP para a api, enviando o email do usuário.
        * Preciso observar a interação após a requisição também.
        */
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", dtNascimento='" + dtNascimento + '\'' +
                ", tipo=" + tipo +
                ", paciente=" + paciente.toString() +
                ", profissional=" + profissional.toString() +
                '}';
    }
}
