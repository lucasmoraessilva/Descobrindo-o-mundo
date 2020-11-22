package com.lighttech.descobrindoomundo.Models;

import java.time.LocalDate;
import com.lighttech.descobrindoomundo.Services.UsuarioHttpService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Usuario {

    private int Id;
    private String Nome;
    private String Sobrenome;
    private String Email;
    private String Senha;
    private String DtNascimento;
    private int Tipo;
    private Paciente Paciente = new Paciente();
    private Profissional Profissional = new Profissional();

    public Usuario(){

    }

    public Usuario(String nome, String sobrenome, String email, String senha, String dataNascimento, int tipo, String nickname) {
        this.setNome(nome);
        this.setSobrenome(sobrenome);
        this.setEmail(email);
        this.setSenha(senha);
        this.setDtNascimento(dataNascimento);
        this.setTipo(tipo);
        this.Paciente.setNickname(nickname);
    }

    public Usuario(String nome, String sobrenome, String email, String senha, String dataNascimento, int tipo, int crm) {
        this.setNome(nome);
        this.setSobrenome(sobrenome);
        this.setEmail(email);
        this.setSenha(senha);
        this.setDtNascimento(dataNascimento);
        this.setTipo(tipo);
        this.Profissional.setCrm(String.valueOf(crm));
    }

    public Usuario(int id, String nome, String sobrenome, String email, String senha, String dataNascimento, int tipo, String nickname) {
        this.setId(id);
        this.setNome(nome);
        this.setSobrenome(sobrenome);
        this.setEmail(email);
        this.setSenha(senha);
        this.setDtNascimento(dataNascimento);
        this.setTipo(tipo);
        this.Paciente.setNickname(nickname);
    }

    public Usuario(int id, String nome, String sobrenome, String email, String senha, String dataNascimento, int tipo, int crm) {
        this.setId(id);
        this.setNome(nome);
        this.setSobrenome(sobrenome);
        this.setEmail(email);
        this.setSenha(senha);
        this.setDtNascimento(dataNascimento);
        this.setTipo(tipo);
        this.Profissional.setCrm(String.valueOf(crm));
    }

    public Usuario(String email, String senha)
    {
        this.setEmail(email);
        this.setSenha(senha);
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        Sobrenome = sobrenome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public String getDtNascimento() {
        return DtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        DtNascimento = dtNascimento;
    }

    public int getTipo() {
        return Tipo;
    }

    public void setTipo(int tipo) {
        Tipo = tipo;
    }

    public com.lighttech.descobrindoomundo.Models.Paciente getPaciente() {
        return Paciente;
    }

    public void setPaciente(com.lighttech.descobrindoomundo.Models.Paciente paciente) {
        Paciente = paciente;
    }

    public com.lighttech.descobrindoomundo.Models.Profissional getProfissional() {
        return Profissional;
    }

    public void setProfissional(com.lighttech.descobrindoomundo.Models.Profissional profissional) {
        Profissional = profissional;
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

    public Call<Usuario> Atualizar(Usuario usuario)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UsuarioHttpService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsuarioHttpService service = retrofit.create(UsuarioHttpService.class);
        final Call<Usuario> requestUsuario = service.Atualizar(usuario);

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
                "id=" + Id +
                ", nome='" + Nome + '\'' +
                ", sobrenome='" + Sobrenome + '\'' +
                ", email='" + Email + '\'' +
                ", senha='" + Senha + '\'' +
                ", DtNascimento='" + DtNascimento + '\'' +
                ", tipo=" + Tipo +
                ", paciente=" + Paciente.toString() +
                ", profissional=" + Profissional.toString() +
                '}';
    }
}
