package com.lighttech.descobrindoomundo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class SelecaoJogo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao_jogo);

        Intent intent = getIntent();
        final int id = Integer.parseInt(intent.getStringExtra("id"));
        final String nome = intent.getStringExtra("nome");
        final String sobrenome = intent.getStringExtra("sobrenome");
        final String email = intent.getStringExtra("email");
        final String senha = intent.getStringExtra("senha");
        final String dtNascimento = intent.getStringExtra("dtNascimento");
        final int tipo = Integer.parseInt(intent.getStringExtra("tipo"));
        final String nickname = intent.getStringExtra("nickname");

        Button btn_selecao_jogo_jogar = findViewById(R.id.btn_selecao_jogo_jogar);
        Button btn_selecao_jogo_selecao = findViewById(R.id.btn_selecao_jogo_selecao);
        Button btn_selecao_jogo_pesquisa = findViewById(R.id.btn_selecao_jogo_pesquisa);
        Button btn_selecao_jogo_alteracoes = findViewById(R.id.btn_selecao_jogo_alteracoes);

        btn_selecao_jogo_jogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NivelDialog("Escolha um nível",id,email);
            }
        });

        btn_selecao_jogo_alteracoes.setOnClickListener(new View.OnClickListener() {
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
                alteracoesIntent.putExtra("nickname", nickname);
                startActivity(alteracoesIntent);
            }
        });

        btn_selecao_jogo_pesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pesquisaIntent = new Intent(getApplicationContext(), Home.class);
                pesquisaIntent.putExtra("id", String.valueOf(id));
                pesquisaIntent.putExtra("email", email);
                startActivity(pesquisaIntent);
            }
        });
    }

    private void NivelDialog(String titulo, int id, String email){
        try {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );

            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);

            Button btn_nivel_01 = new Button(this);
            btn_nivel_01.setText("01");
            btn_nivel_01.setLayoutParams(layoutParams2);
            btn_nivel_01.setOnClickListener(new MyOnClickListenerNivel01(id,email));

            Button btn_nivel_02 = new Button(this);
            btn_nivel_02.setText("02");
            btn_nivel_02.setLayoutParams(layoutParams2);
            btn_nivel_02.setOnClickListener(new MyOnClickListenerNivel02(id,email));

            Button btn_nivel_03 = new Button(this);
            btn_nivel_03.setText("03");
            btn_nivel_03.setLayoutParams(layoutParams2);
            btn_nivel_03.setOnClickListener(new MyOnClickListenerNivel03(id,email));

            linearLayout.addView(btn_nivel_01);
            linearLayout.addView(btn_nivel_02);
            linearLayout.addView(btn_nivel_03);

            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(titulo)
                    .setCancelable(false)
                    .setView(linearLayout)
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).create();
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class MyOnClickListenerNivel01 implements View.OnClickListener{

        private int id;
        private String email;

        public MyOnClickListenerNivel01(int id, String email) {
            this.id = id;
            this.email = email;
        }

        @Override
        public void onClick(View view) {
            Intent ActivityJogoNivel01 = new Intent(getApplicationContext(),JogoNivel01.class);
            ActivityJogoNivel01.putExtra("id", String.valueOf(id));
            ActivityJogoNivel01.putExtra("email",email);
            startActivity(ActivityJogoNivel01);
        }
    }

    class MyOnClickListenerNivel02 implements View.OnClickListener{

        private int id;
        private String email;

        public MyOnClickListenerNivel02(int id, String email) {
            this.id = id;
            this.email = email;
        }

        @Override
        public void onClick(View view) {
            Intent ActivityJogoNivel02 = new Intent(getApplicationContext(),JogoNivel02.class);
            ActivityJogoNivel02.putExtra("id", String.valueOf(id));
            ActivityJogoNivel02.putExtra("email",email);
            startActivity(ActivityJogoNivel02);
        }
    }

    class MyOnClickListenerNivel03 implements View.OnClickListener{

        private int id;
        private String email;

        public MyOnClickListenerNivel03(int id, String email) {
            this.id = id;
            this.email = email;
        }

        @Override
        public void onClick(View view) {
            Intent ActivityJogoNivel03 = new Intent(getApplicationContext(),JogoNivel03.class);
            ActivityJogoNivel03.putExtra("id", String.valueOf(id));
            ActivityJogoNivel03.putExtra("email",email);
            startActivity(ActivityJogoNivel03);
        }
    }
}