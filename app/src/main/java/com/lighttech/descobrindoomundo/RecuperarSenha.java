package com.lighttech.descobrindoomundo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class RecuperarSenha extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        WebView wv_recuperar_senha = findViewById(R.id.wv_recuperar_senha);
        wv_recuperar_senha.setWebViewClient(new WebViewClient());
        wv_recuperar_senha.loadUrl("http://192.168.0.73/");
        WebSettings webSettings = wv_recuperar_senha.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}