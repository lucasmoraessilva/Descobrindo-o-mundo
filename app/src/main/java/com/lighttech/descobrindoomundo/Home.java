package com.lighttech.descobrindoomundo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.lighttech.descobrindoomundo.Models.Partida;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        DialogEncerrar("Atenção", "Deseja encerrar o aplicativo?");
    }

    @Override
    public boolean onKeyUp(int KeyCode, KeyEvent event){

        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            final EditText et_home_pesquisar_partida = findViewById(R.id.et_home_pesquisar_partida);

            String et_home_pesquisar_partida_text = et_home_pesquisar_partida.getText().toString();

            if (!et_home_pesquisar_partida_text.isEmpty()){
                Partida partidaPesquisar = new Partida();
                Call<ArrayList<Partida>> partidaCall = partidaPesquisar.Pesquisar(et_home_pesquisar_partida_text);

                partidaCall.enqueue(new Callback<ArrayList<Partida>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Partida>> call, Response<ArrayList<Partida>> response) {
                        if(response.code() == 200 && !response.body().isEmpty()){

                            ArrayList<String> partidasBuscadas = new ArrayList<>();

                            for (Partida partida : response.body()){
                                partidasBuscadas.add(partida.listarDadosResposta());
                            }

                            ListView lv_home_partidas = findViewById(R.id.lv_home_partidas);
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, partidasBuscadas);
                            lv_home_partidas.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Partida>> call, Throwable t) {
                        Dialog("Atenção", "Não foi possível fazer a pesquisa, vefifique seu status de internet");
                    }
                });
            }

            return true;
        }
        else{
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        EditText et_home_pesquisar_partida = findViewById(R.id.et_home_pesquisar_partida);
        Button btn_home_selecao = findViewById(R.id.btn_home_selecao);
        Button btn_home_alteracoes = findViewById(R.id.btn_home_alteracoes);
        ListView lv_home_partidas = findViewById(R.id.lv_home_partidas);

        Intent intent = getIntent();
        final int id = Integer.parseInt(intent.getStringExtra("id"));
        final String nome = intent.getStringExtra("nome");
        final String sobrenome = intent.getStringExtra("sobrenome");
        final String email = intent.getStringExtra("email");
        final String senha = intent.getStringExtra("senha");
        final String dtNascimento = intent.getStringExtra("dtNascimento");
        final int tipo = Integer.parseInt(intent.getStringExtra("tipo"));
        final int idPaciente = Integer.parseInt(intent.getStringExtra("idPaciente"));
        final String nickname = intent.getStringExtra("nickname");

        btn_home_selecao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selecaoIntent = new Intent(getApplicationContext(), SelecaoJogo.class);
                selecaoIntent.putExtra("id", String.valueOf(id));
                selecaoIntent.putExtra("email", email);
                selecaoIntent.putExtra("nome", nome);
                selecaoIntent.putExtra("sobrenome", sobrenome);
                selecaoIntent.putExtra("senha", senha);
                selecaoIntent.putExtra("dtNascimento", dtNascimento);
                selecaoIntent.putExtra("tipo", String.valueOf(tipo));
                selecaoIntent.putExtra("idPaciente", String.valueOf(idPaciente));
                selecaoIntent.putExtra("nickname", nickname);
                startActivity(selecaoIntent);
            }
        });

        btn_home_alteracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alteracoesIntent = new Intent(getApplicationContext(), AlterarInformacoes.class);
                alteracoesIntent.putExtra("id", String.valueOf(id));
                alteracoesIntent.putExtra("email", email);
                alteracoesIntent.putExtra("nome", nome);
                alteracoesIntent.putExtra("sobrenome", sobrenome);
                alteracoesIntent.putExtra("senha", senha);
                alteracoesIntent.putExtra("dtNascimento", dtNascimento);
                alteracoesIntent.putExtra("tipo", String.valueOf(tipo));
                alteracoesIntent.putExtra("idPaciente", String.valueOf(idPaciente));
                alteracoesIntent.putExtra("nickname", nickname);
                startActivity(alteracoesIntent);
            }
        });
    }

    private void Dialog(String titulo, String mensagem){
        try {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(titulo)
                    .setCancelable(false)
                    .setMessage(mensagem)
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

    private void DialogEncerrar(String titulo, String mensagem){
        try {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(titulo)
                    .setCancelable(false)
                    .setMessage(mensagem)
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                            System.exit(0);
                        }
                    }).create();
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}