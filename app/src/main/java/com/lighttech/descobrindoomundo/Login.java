package com.lighttech.descobrindoomundo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.lighttech.descobrindoomundo.Models.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final TextView tvEmail_login = findViewById(R.id.txt_email);
        final TextView tvSenha_login = findViewById(R.id.txt_senha);
        final Button btnEntrar = findViewById(R.id.btn_entrar);
        final TextView tvEsqueciSenha = findViewById(R.id.lnk_esqueci_minha_senha);
        final Button btnCadastre_se = findViewById(R.id.btn_cadastre_se);

        btnEntrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String email = tvEmail_login.getText().toString();
                String senha = tvSenha_login.getText().toString();

                Usuario usario = new Usuario();
                Call<Usuario> usuarioCall = usario.Login(email,senha);

                usuarioCall.enqueue(new Callback<Usuario>() {
                    private static final String TAG = "sumer";

                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                        if (!response.isSuccessful()){
                            UsuarioDialog("Usuário não encontrado");
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(),Home.class);
                            intent.putExtra("id", String.valueOf(response.body().getId()));
                            intent.putExtra("nome", response.body().getNome());
                            intent.putExtra("sobrenome", response.body().getSobrenome());
                            intent.putExtra("email", response.body().getEmail());
                            intent.putExtra("senha", response.body().getSenha());
                            intent.putExtra("dtNascimento", response.body().getDtNascimento());
                            intent.putExtra("tipo", String.valueOf(response.body().getTipo()));
                            intent.putExtra("idPaciente", String.valueOf(response.body().getPaciente().getId()));
                            intent.putExtra("nickname", response.body().getPaciente().getNickname());

                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Log.e(TAG,"Erro " + t.getMessage());
                    }
                });
            }
        });

        btnCadastre_se.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Cadastre_se.class);
                startActivity(intent);
            }
        });

        tvEsqueciSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RecuperarSenha.class);
                startActivity(intent);
            }
        });
    }

    private void UsuarioDialog(String messagem){
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
}
