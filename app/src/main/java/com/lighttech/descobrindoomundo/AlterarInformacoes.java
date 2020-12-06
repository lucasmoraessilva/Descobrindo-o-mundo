package com.lighttech.descobrindoomundo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.lighttech.descobrindoomundo.Models.Paciente;
import com.lighttech.descobrindoomundo.Models.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlterarInformacoes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_informacoes);

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

        Button btn_alterar_informacoes_pesquisa = findViewById(R.id.btn_alterar_informacoes_pesquisa);
        Button btn_alterar_informacoes_selecao = findViewById(R.id.btn_alterar_informacoes_jogar);
        Button btn_alterar_informacoes_alterar = findViewById(R.id.btn_alterar_informacoes_alteracoes);
        final Button btn_alterar_informacoes_nome = findViewById(R.id.btn_alterar_informacoes_nome);
        final Button btn_alterar_informacoes_sobrenome = findViewById(R.id.btn_alterar_informacoes_sobrenome);
        final Button btn_alterar_informacoes_nick = findViewById(R.id.btn_alterar_informacoes_nick);
        final Button btn_alterar_informacoes_email = findViewById(R.id.btn_alterar_informacoes_email);
        final Button btn_alterar_informacoes_dt_nascimento = findViewById(R.id.btn_alterar_informacoes_dt_nascimento);
        final Button btn_alterar_informacoes_senha = findViewById(R.id.btn_alterar_informacoes_senha);
        final Button btn_alterar_informacoes_salvar = findViewById(R.id.btn_alterar_informacoes_salvar);
        final EditText et_alterar_informacoes_nome = findViewById(R.id.et_alterar_informacoes_nome);
        final EditText et_alterar_informacoes_sobrenome = findViewById(R.id.et_alterar_informacoes_sobrenome);
        final EditText et_alterar_informacoes_nick = findViewById(R.id.et_alterar_informacoes_nick);
        final EditText et_alterar_informacoes_email = findViewById(R.id.et_alterar_informacoes_email);
        final EditText et_alterar_informacoes_dt_nascimento = findViewById(R.id.et_alterar_informacoes_dt_nascimento);
        final EditText et_alterar_informacoes_senha = findViewById(R.id.et_alterar_informacoes_senha);

        final Usuario usuarioPaciente = new Usuario(id,nome,sobrenome, email, senha,dtNascimento,tipo,idPaciente,nickname);
        final Usuario usuarioPacienteAtualizado = new Usuario(
                usuarioPaciente.getId(),
                usuarioPaciente.getNome(),
                usuarioPaciente.getSobrenome(),
                usuarioPaciente.getEmail(),
                usuarioPaciente.getSenha(),
                usuarioPaciente.getDtNascimento(),
                usuarioPaciente.getTipo(),
                usuarioPaciente.getPaciente().getId(),
                usuarioPaciente.getPaciente().getNickname()
        );

        et_alterar_informacoes_nome.setHint(et_alterar_informacoes_nome.getHint() + usuarioPaciente.getNome());
        et_alterar_informacoes_sobrenome.setHint(et_alterar_informacoes_sobrenome.getHint() + usuarioPaciente.getSobrenome());
        et_alterar_informacoes_nick.setHint(et_alterar_informacoes_nick.getHint() + usuarioPaciente.getPaciente().getNickname());
        et_alterar_informacoes_email.setHint(et_alterar_informacoes_email.getHint() + usuarioPaciente.getEmail());
        et_alterar_informacoes_dt_nascimento.setHint(et_alterar_informacoes_dt_nascimento.getHint() + usuarioPaciente.getDtNascimento());

        btn_alterar_informacoes_nome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_alterar_informacoes_nome.setEnabled(true);
                et_alterar_informacoes_nome.requestFocus();
                et_alterar_informacoes_nome.setText(usuarioPaciente.getNome());
                InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                imm.showSoftInput(et_alterar_informacoes_nome, InputMethodManager.SHOW_IMPLICIT);
                btn_alterar_informacoes_nome.setEnabled(false);
                btn_alterar_informacoes_salvar.setEnabled(true);
            }
        });

        btn_alterar_informacoes_sobrenome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_alterar_informacoes_sobrenome.setEnabled(true);
                et_alterar_informacoes_sobrenome.requestFocus();
                et_alterar_informacoes_sobrenome.setText(usuarioPaciente.getSobrenome());
                InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                imm.showSoftInput(et_alterar_informacoes_sobrenome, InputMethodManager.SHOW_IMPLICIT);
                btn_alterar_informacoes_sobrenome.setEnabled(false);
                btn_alterar_informacoes_salvar.setEnabled(true);
            }
        });

        btn_alterar_informacoes_nick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_alterar_informacoes_nick.setEnabled(true);
                et_alterar_informacoes_nick.requestFocus();
                et_alterar_informacoes_nick.setText(usuarioPaciente.getPaciente().getNickname());
                InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                imm.showSoftInput(et_alterar_informacoes_nick, InputMethodManager.SHOW_IMPLICIT);
                btn_alterar_informacoes_nick.setEnabled(false);
                btn_alterar_informacoes_salvar.setEnabled(true);
            }
        });

        btn_alterar_informacoes_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_alterar_informacoes_email.setEnabled(true);
                et_alterar_informacoes_email.requestFocus();
                et_alterar_informacoes_email.setText(usuarioPaciente.getEmail());
                InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                imm.showSoftInput(et_alterar_informacoes_email, InputMethodManager.SHOW_IMPLICIT);
                btn_alterar_informacoes_email.setEnabled(false);
                btn_alterar_informacoes_salvar.setEnabled(true);
            }
        });

        btn_alterar_informacoes_dt_nascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_alterar_informacoes_dt_nascimento.setEnabled(true);
                et_alterar_informacoes_dt_nascimento.requestFocus();
                et_alterar_informacoes_dt_nascimento.setText(usuarioPaciente.getDtNascimento());
                InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                imm.showSoftInput(et_alterar_informacoes_dt_nascimento, InputMethodManager.SHOW_IMPLICIT);
                btn_alterar_informacoes_dt_nascimento.setEnabled(false);
                btn_alterar_informacoes_salvar.setEnabled(true);
            }
        });

        btn_alterar_informacoes_senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_alterar_informacoes_senha.setEnabled(true);
                et_alterar_informacoes_senha.requestFocus();
                et_alterar_informacoes_senha.setText(usuarioPaciente.getSenha());
                InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                imm.showSoftInput(et_alterar_informacoes_senha, InputMethodManager.SHOW_IMPLICIT);
                btn_alterar_informacoes_senha.setEnabled(false);
                btn_alterar_informacoes_salvar.setEnabled(true);
            }
        });

        btn_alterar_informacoes_selecao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selecaoIntent = new Intent(getApplicationContext(), SelecaoJogo.class);
                selecaoIntent.putExtra("id", String.valueOf(usuarioPaciente.getId()));
                selecaoIntent.putExtra("email", usuarioPaciente.getEmail());
                selecaoIntent.putExtra("nome", usuarioPaciente.getNome());
                selecaoIntent.putExtra("sobrenome", usuarioPaciente.getSobrenome());
                selecaoIntent.putExtra("senha", usuarioPaciente.getSenha());
                selecaoIntent.putExtra("dtNascimento", usuarioPaciente.getDtNascimento());
                selecaoIntent.putExtra("tipo", String.valueOf(usuarioPaciente.getTipo()));
                selecaoIntent.putExtra("idPaciente", String.valueOf(usuarioPaciente.getPaciente().getId()));
                selecaoIntent.putExtra("nickname", usuarioPaciente.getPaciente().getNickname());
                startActivity(selecaoIntent);
            }
        });

        btn_alterar_informacoes_pesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pesquisaIntent = new Intent(getApplicationContext(), Home.class);
                pesquisaIntent.putExtra("id", String.valueOf(usuarioPaciente.getId()));
                pesquisaIntent.putExtra("email", usuarioPaciente.getEmail());
                pesquisaIntent.putExtra("nome", usuarioPaciente.getNome());
                pesquisaIntent.putExtra("sobrenome", usuarioPaciente.getSobrenome());
                pesquisaIntent.putExtra("senha", usuarioPaciente.getSenha());
                pesquisaIntent.putExtra("dtNascimento", usuarioPaciente.getDtNascimento());
                pesquisaIntent.putExtra("tipo", String.valueOf(usuarioPaciente.getTipo()));
                pesquisaIntent.putExtra("idPaciente", String.valueOf(usuarioPaciente.getPaciente().getId()));
                pesquisaIntent.putExtra("nickname", usuarioPaciente.getPaciente().getNickname());
                startActivity(pesquisaIntent);
            }
        });

        btn_alterar_informacoes_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String et_alterar_informacoes_nome_text = et_alterar_informacoes_nome.getText().toString();
                String et_alterar_informacoes_sobrenome_text = et_alterar_informacoes_sobrenome.getText().toString();
                String et_alterar_informacoes_nick_text = et_alterar_informacoes_nick.getText().toString();
                String et_alterar_informacoes_email_text = et_alterar_informacoes_email.getText().toString();
                String et_alterar_informacoes_dt_nascimento_text = et_alterar_informacoes_dt_nascimento.getText().toString();
                String et_alterar_informacoes_senha_text = et_alterar_informacoes_senha.getText().toString();

                if (et_alterar_informacoes_nome.isEnabled()){
                    if (!et_alterar_informacoes_nome_text.isEmpty()){
                        usuarioPacienteAtualizado.setNome(et_alterar_informacoes_nome_text);
                    }
                }

                if (et_alterar_informacoes_sobrenome.isEnabled()){
                    if (!et_alterar_informacoes_sobrenome_text.isEmpty()){
                        usuarioPacienteAtualizado.setSobrenome(et_alterar_informacoes_sobrenome_text);
                    }
                }

                if(et_alterar_informacoes_nick.isEnabled()){
                    if (!et_alterar_informacoes_nick_text.isEmpty()){
                        usuarioPacienteAtualizado.getPaciente().setNickname(et_alterar_informacoes_nick_text);
                    }
                }

                if(et_alterar_informacoes_email.isEnabled()){
                    if (!et_alterar_informacoes_email_text.isEmpty()){
                        usuarioPacienteAtualizado.setEmail(et_alterar_informacoes_email_text);
                    }
                }

                if(et_alterar_informacoes_dt_nascimento.isEnabled()){
                    if (!et_alterar_informacoes_dt_nascimento_text.isEmpty()){
                        usuarioPacienteAtualizado.setDtNascimento(et_alterar_informacoes_dt_nascimento_text);
                    }
                }

                if(et_alterar_informacoes_senha.isEnabled()){
                    if (!et_alterar_informacoes_senha_text.isEmpty()){
                        usuarioPacienteAtualizado.setSenha(et_alterar_informacoes_senha_text);
                    }
                }

                if(et_alterar_informacoes_nome_text.isEmpty() && et_alterar_informacoes_nome.isEnabled()){
                    Dialog("Atenção", "O campo 'Nome' está vazio");
                }
                else if(et_alterar_informacoes_sobrenome_text.isEmpty() && et_alterar_informacoes_sobrenome.isEnabled()){
                    Dialog("Atenção", "O campo 'Sobrenome' está vazio");
                }
                else if(et_alterar_informacoes_nick_text.isEmpty() && et_alterar_informacoes_nick.isEnabled()){
                    Dialog("Atenção", "O campo 'Nick' está vazio");
                }
                else if(et_alterar_informacoes_email_text.isEmpty() && et_alterar_informacoes_email.isEnabled()){
                    Dialog("Atenção", "O campo 'Email' está vazio");
                }
                else if(et_alterar_informacoes_dt_nascimento_text.isEmpty() && et_alterar_informacoes_dt_nascimento.isEnabled()){
                    Dialog("Atenção", "O campo 'Data de nascimento' está vazio");
                }
                else if(et_alterar_informacoes_senha_text.isEmpty() && et_alterar_informacoes_senha.isEnabled()){
                    Dialog("Atenção", "O campo 'Senha' está vazio");
                }
                else{
                    Log.i("Usuario", usuarioPacienteAtualizado.toString());
                    Call<Usuario> usuarioCall = usuarioPacienteAtualizado.Atualizar(usuarioPacienteAtualizado);

                    usuarioCall.enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                            if(response.code() == 200){
                                RespostaDialog("Usuário atualizado", "Suas informações foram atualizadas com sucesso", usuarioPacienteAtualizado);
                            }
                            else {
                                RespostaDialog("Atenção", "Não foi possível enviar os dados de atualização.\n\nVerifique se as informações foram preenchidas de forma correta", usuarioPaciente);
                            }
                        }

                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {
                            RespostaDialog("Atenção", "Não foi possível enviar os dados de atualização.\n\nVerifique seu status de internet", usuarioPaciente);
                        }
                    });
                }
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

    private void RespostaDialog(String titulo, String mensagem, final Usuario usuario){
        try {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(titulo)
                    .setCancelable(false)
                    .setMessage(mensagem)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), AlterarInformacoes.class);
                            intent.putExtra("id", String.valueOf(usuario.getId()));
                            intent.putExtra("email", usuario.getEmail());
                            intent.putExtra("nome", usuario.getNome());
                            intent.putExtra("sobrenome", usuario.getSobrenome());
                            intent.putExtra("senha", usuario.getSenha());
                            intent.putExtra("dtNascimento", usuario.getDtNascimento());
                            intent.putExtra("tipo", String.valueOf(usuario.getTipo()));
                            intent.putExtra("idPaciente", String.valueOf(usuario.getPaciente().getId()));
                            intent.putExtra("nickname", usuario.getPaciente().getNickname());
                            startActivity(intent);
                        }
                    }).create();
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}