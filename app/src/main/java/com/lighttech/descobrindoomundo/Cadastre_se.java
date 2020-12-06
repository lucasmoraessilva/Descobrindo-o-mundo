package com.lighttech.descobrindoomundo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import com.lighttech.descobrindoomundo.Models.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cadastre_se extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastre_se);
        final Button btnCadastrar = findViewById(R.id.btn_cadastro_cadastrar);
        final EditText et_cadastreSe_nome = findViewById(R.id.et_cadastro_nome);
        final EditText et_cadastreSe_sbrnome = findViewById(R.id.et_cadastro_sbrnome);
        final EditText et_cadastreSe_email = findViewById(R.id.et_cadastro_email);
        final EditText et_cadastreSe_senha = findViewById(R.id.et_cadastro_senha);
        final EditText et_cadastreSe_confirmacao_senha = findViewById(R.id.et_cadastro_confirmacao_senha);
        final EditText et_cadastreSe_data_nascimento = findViewById(R.id.et_cadastro_data_nascimento);
        final EditText et_cadastreSe_nickname = findViewById(R.id.et_cadastro_nickname);
        final EditText et_cadastreSe_crm = findViewById(R.id.et_cadastro_crm);
        final RadioGroup rg_cadastreSe = findViewById(R.id.rd_btn_group);

        rg_cadastreSe.check(R.id.rd_btn_paciente);
        et_cadastreSe_crm.setEnabled(false);

        rg_cadastreSe.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rd_btn_paciente:
                        et_cadastreSe_crm.setEnabled(false);
                        et_cadastreSe_crm.setText("");
                        et_cadastreSe_nickname.setEnabled(true);
                        break;

                    case R.id.rd_btn_profissional:
                        et_cadastreSe_nickname.setEnabled(false);
                        et_cadastreSe_nickname.setText("");
                        et_cadastreSe_crm.setEnabled(true);
                        break;
                }
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = et_cadastreSe_nome.getText().toString();
                String sobrenome = et_cadastreSe_sbrnome.getText().toString();
                String email = et_cadastreSe_email.getText().toString();
                String senha = et_cadastreSe_senha.getText().toString();
                String dtNascimento = et_cadastreSe_data_nascimento.getText().toString();
                String nickname = et_cadastreSe_nickname.getText().toString();
                String crm = et_cadastreSe_crm.getText().toString();
                int tipo = 0;
                if (crm.isEmpty() && !nickname.isEmpty()){
                    tipo = 1;
                } else if (nickname.isEmpty() && !crm.isEmpty()) {
                    tipo = 2;
                }

                switch (tipo){
                    case 1:
                        String[] arrayData = dtNascimento.split("/");
                        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                        LocalDate dataFormatada = LocalDate.parse(arrayData[2] + "-" + arrayData[1] + "-" + arrayData[0]);
                        String dataFinalFormatada = formatoData.format(dataFormatada);

                        Usuario usuarioPaciente = new Usuario(nome, sobrenome, email, senha, dataFinalFormatada, tipo, nickname);
                        Call<Usuario> usuarioCallPaciente = usuarioPaciente.Cadastrar(usuarioPaciente);

                        usuarioCallPaciente.enqueue(new Callback<Usuario>() {
                            @Override
                            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                if (!response.isSuccessful()){
                                    UsuarioDialogErro("Não foi possível cadastrar suas informações");
                                    Log.e("ERRO: ", response.toString());
                                }
                                else{
                                    UsuarioDialogSucesso("Usuário cadastrado");
                                }
                            }

                            @Override
                            public void onFailure(Call<Usuario> call, Throwable t) {

                            }
                        });
                        break;

                    case 2:
                        Usuario usuarioProfissional = new Usuario(nome, sobrenome, email, senha, dtNascimento, tipo, Integer.parseInt(crm));
                        Log.i("Usuario profissional", usuarioProfissional.toString());
                        Call<Usuario> usuarioCallProfissional = usuarioProfissional.Cadastrar(usuarioProfissional);

                        usuarioCallProfissional.enqueue(new Callback<Usuario>() {
                            @Override
                            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                if (!response.isSuccessful()){
                                    UsuarioDialogErro("Não foi possível cadastrar suas informações");
                                    Log.e("ERRO: ", response.toString());
                                }
                                else{
                                    UsuarioDialogSucesso("Usuário cadastrado");
                                }
                            }

                            @Override
                            public void onFailure(Call<Usuario> call, Throwable t) {
                                Log.e("ERRO: ", t.getMessage());
                            }
                        });
                        break;
                }
            }
        });
    }

    private void UsuarioDialogErro(String messagem){
        try {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setMessage(messagem)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).create();
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void UsuarioDialogSucesso(String messagem){
        try {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setMessage(messagem)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                        }
                    }).create();
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}