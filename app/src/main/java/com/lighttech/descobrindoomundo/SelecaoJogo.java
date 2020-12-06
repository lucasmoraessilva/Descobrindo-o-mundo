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

import com.lighttech.descobrindoomundo.Models.Usuario;

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
        final int idPaciente = Integer.parseInt(intent.getStringExtra("idPaciente"));
        final String nickname = intent.getStringExtra("nickname");

        final Usuario usuario = new Usuario(id,nome,sobrenome,email,senha,dtNascimento,tipo,idPaciente,nickname);

        Button btn_selecao_jogo_jogar = findViewById(R.id.btn_selecao_jogo_jogar);
        Button btn_selecao_jogo_selecao = findViewById(R.id.btn_selecao_jogo_selecao);
        Button btn_selecao_jogo_pesquisa = findViewById(R.id.btn_selecao_jogo_pesquisa);
        Button btn_selecao_jogo_alteracoes = findViewById(R.id.btn_selecao_jogo_alteracoes);

        btn_selecao_jogo_jogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NivelDialog("Escolha um nível",usuario);
            }
        });

        btn_selecao_jogo_alteracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alteracoesIntent = new Intent(getApplicationContext(), AlterarInformacoes.class);
                alteracoesIntent.putExtra("id", String.valueOf(usuario.getId()));
                alteracoesIntent.putExtra("email", usuario.getEmail());
                alteracoesIntent.putExtra("nome", usuario.getNome());
                alteracoesIntent.putExtra("sobrenome", usuario.getSobrenome());
                alteracoesIntent.putExtra("senha", usuario.getSenha());
                alteracoesIntent.putExtra("dtNascimento", usuario.getDtNascimento());
                alteracoesIntent.putExtra("tipo", String.valueOf(usuario.getTipo()));
                alteracoesIntent.putExtra("idPaciente", String.valueOf(usuario.getPaciente().getId()));
                alteracoesIntent.putExtra("nickname", usuario.getPaciente().getNickname());
                startActivity(alteracoesIntent);
            }
        });

        btn_selecao_jogo_pesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pesquisaIntent = new Intent(getApplicationContext(), Home.class);
                pesquisaIntent.putExtra("id", String.valueOf(usuario.getId()));
                pesquisaIntent.putExtra("email", usuario.getEmail());
                pesquisaIntent.putExtra("nome", usuario.getNome());
                pesquisaIntent.putExtra("sobrenome", usuario.getSobrenome());
                pesquisaIntent.putExtra("senha", usuario.getSenha());
                pesquisaIntent.putExtra("dtNascimento", usuario.getDtNascimento());
                pesquisaIntent.putExtra("tipo", String.valueOf(usuario.getTipo()));
                pesquisaIntent.putExtra("idPaciente", String.valueOf(usuario.getPaciente().getId()));
                pesquisaIntent.putExtra("nickname", usuario.getPaciente().getNickname());
                startActivity(pesquisaIntent);
            }
        });
    }

    private void NivelDialog(String titulo, Usuario usuario){
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
            btn_nivel_01.setOnClickListener(new MyOnClickListenerNivel01(usuario));

            Button btn_nivel_02 = new Button(this);
            btn_nivel_02.setText("02");
            btn_nivel_02.setLayoutParams(layoutParams2);
            btn_nivel_02.setOnClickListener(new MyOnClickListenerNivel02(usuario));

            Button btn_nivel_03 = new Button(this);
            btn_nivel_03.setText("03");
            btn_nivel_03.setLayoutParams(layoutParams2);
            btn_nivel_03.setOnClickListener(new MyOnClickListenerNivel03(usuario));

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

        private Usuario usuario;

        public MyOnClickListenerNivel01(Usuario usuario) {
            this.usuario = usuario;
        }

        @Override
        public void onClick(View view) {
            Intent ActivityJogoNivel01 = new Intent(getApplicationContext(),JogoNivel01.class);
            ActivityJogoNivel01.putExtra("id", String.valueOf(usuario.getId()));
            ActivityJogoNivel01.putExtra("email",usuario.getEmail());
            ActivityJogoNivel01.putExtra("nome", usuario.getNome());
            ActivityJogoNivel01.putExtra("sobrenome", usuario.getSobrenome());
            ActivityJogoNivel01.putExtra("senha", usuario.getSenha());
            ActivityJogoNivel01.putExtra("dtNascimento", usuario.getDtNascimento());
            ActivityJogoNivel01.putExtra("tipo", String.valueOf(usuario.getTipo()));
            ActivityJogoNivel01.putExtra("idPaciente", String.valueOf(usuario.getPaciente().getId()));
            ActivityJogoNivel01.putExtra("nickname", usuario.getPaciente().getNickname());
            startActivity(ActivityJogoNivel01);
        }
    }

    class MyOnClickListenerNivel02 implements View.OnClickListener{

        private Usuario usuario;

        public MyOnClickListenerNivel02(Usuario usuario) {
            this.usuario = usuario;
        }

        @Override
        public void onClick(View view) {
            Intent ActivityJogoNivel02 = new Intent(getApplicationContext(),JogoNivel02.class);
            ActivityJogoNivel02.putExtra("id", String.valueOf(usuario.getId()));
            ActivityJogoNivel02.putExtra("email",usuario.getEmail());
            ActivityJogoNivel02.putExtra("nome", usuario.getNome());
            ActivityJogoNivel02.putExtra("sobrenome", usuario.getSobrenome());
            ActivityJogoNivel02.putExtra("senha", usuario.getSenha());
            ActivityJogoNivel02.putExtra("dtNascimento", usuario.getDtNascimento());
            ActivityJogoNivel02.putExtra("tipo", String.valueOf(usuario.getTipo()));
            ActivityJogoNivel02.putExtra("idPaciente", String.valueOf(usuario.getPaciente().getId()));
            ActivityJogoNivel02.putExtra("nickname", usuario.getPaciente().getNickname());
            startActivity(ActivityJogoNivel02);
        }
    }

    class MyOnClickListenerNivel03 implements View.OnClickListener{

        private Usuario usuario;

        public MyOnClickListenerNivel03(Usuario usuario) {
            this.usuario = usuario;
        }

        @Override
        public void onClick(View view) {
            Intent ActivityJogoNivel03 = new Intent(getApplicationContext(),JogoNivel03.class);
            ActivityJogoNivel03.putExtra("id", String.valueOf(usuario.getId()));
            ActivityJogoNivel03.putExtra("email",usuario.getEmail());
            ActivityJogoNivel03.putExtra("nome", usuario.getNome());
            ActivityJogoNivel03.putExtra("sobrenome", usuario.getSobrenome());
            ActivityJogoNivel03.putExtra("senha", usuario.getSenha());
            ActivityJogoNivel03.putExtra("dtNascimento", usuario.getDtNascimento());
            ActivityJogoNivel03.putExtra("tipo", String.valueOf(usuario.getTipo()));
            ActivityJogoNivel03.putExtra("idPaciente", String.valueOf(usuario.getPaciente().getId()));
            ActivityJogoNivel03.putExtra("nickname", usuario.getPaciente().getNickname());

            startActivity(ActivityJogoNivel03);
        }
    }
}