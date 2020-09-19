package com.lighttech.descobrindoomundo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;

import com.lighttech.descobrindoomundo.Models.Usuario;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String login = "Teste";
        String senha = "Teste2";

        Usuario usuario = new Usuario(login,senha);
        usuario.Login(usuario.getEmail(), usuario.getSenha());
    }
}