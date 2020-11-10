package com.lighttech.descobrindoomundo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lighttech.descobrindoomundo.Models.Palavra;
import com.lighttech.descobrindoomundo.Models.Partida;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JogoNivel02 extends AppCompatActivity {

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_nivel_02);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_FULLSCREEN);

        Intent intent = getIntent();
        final int id = Integer.parseInt(intent.getStringExtra("id"));
        final String email = intent.getStringExtra("email");
        final LocalTime horaInicial = LocalTime.now();
        final int tentativas = 0;

        final FrameLayout fl_jogo_nivel_02 = findViewById(R.id.fl_jogo_nivel_02);
        final ImageView iv_jogo_nivel_02 = findViewById(R.id.iv_jogo_nivel_02);
        final LinearLayout ll_jogo_nivel_02_encaixes = findViewById(R.id.ll_jogo_nivel_02_encaixes);
        final LinearLayout ll_jogo_nivel_02_encaixe_01 = findViewById(R.id.ll_jogo_nivel_02_encaixe_01);
        final LinearLayout ll_jogo_nivel_02_encaixe_02 = findViewById(R.id.ll_jogo_nivel_02_encaixe_02);
        final LinearLayout ll_jogo_nivel_02_encaixe_03 = findViewById(R.id.ll_jogo_nivel_02_encaixe_03);
        final LinearLayout ll_jogo_nivel_02_silabas = findViewById(R.id.ll_jogo_nivel_02_silabas);
        final TextView tv_jogo_nivel_02_slb_01 = findViewById(R.id.tv_jogo_nivel_02_slb_01);
        final TextView tv_jogo_nivel_02_slb_02 = findViewById(R.id.tv_jogo_nivel_02_slb_02);
        final TextView tv_jogo_nivel_02_slb_03 = findViewById(R.id.tv_jogo_nivel_02_slb_03);
        final TextView tv_jogo_nivel_02_slb_04 = findViewById(R.id.tv_jogo_nivel_02_slb_04);
        final TextView tv_jogo_nivel_02_slb_05 = findViewById(R.id.tv_jogo_nivel_02_slb_05);
        final TextView tv_jogo_nivel_02_slb_06 = findViewById(R.id.tv_jogo_nivel_02_slb_06);
        final Button btn_jogo_nivel_02_verificar = findViewById(R.id.btn_jogo_nivel_02_verificar);
        final Button btn_jogo_nivel_02_ajuda = findViewById(R.id.btn_jogo_nivel_02_ajuda);
        final Button btn_jogo_nivel_02_desistir = findViewById(R.id.btn_jogo_nivel_02_desistir);

        tv_jogo_nivel_02_slb_01.setOnLongClickListener(new MyOnLongClickListener());
        tv_jogo_nivel_02_slb_02.setOnLongClickListener(new MyOnLongClickListener());
        tv_jogo_nivel_02_slb_03.setOnLongClickListener(new MyOnLongClickListener());
        tv_jogo_nivel_02_slb_04.setOnLongClickListener(new MyOnLongClickListener());
        tv_jogo_nivel_02_slb_05.setOnLongClickListener(new MyOnLongClickListener());
        tv_jogo_nivel_02_slb_06.setOnLongClickListener(new MyOnLongClickListener());

        ll_jogo_nivel_02_encaixe_01.setOnDragListener(new MyOnDragListenerReceptor());
        ll_jogo_nivel_02_encaixe_02.setOnDragListener(new MyOnDragListenerReceptor());
        ll_jogo_nivel_02_encaixe_03.setOnDragListener(new MyOnDragListenerReceptor());
        fl_jogo_nivel_02.setOnDragListener(new MyOnDragListenerFrameLayout());

        Palavra palavra = new Palavra();
        Call<Palavra> palavraCall = palavra.buscar(2);

        palavraCall.enqueue(new Callback<Palavra>() {
            @Override
            public void onResponse(Call<Palavra> call, Response<Palavra> response) {
                if (!response.isSuccessful()){
                    Log.i("Não foi",response.body().toString());
                }
                else{
                    String[] silabasCorretas = response.body().getSilabas().getSilabasCorretas().split(",");
                    String[] silabasIncorretas = response.body().getSilabas().getSilabasIncorretas().split(",");

                    btn_jogo_nivel_02_verificar.setOnClickListener(new MyOnClickListenerBtnVerificar(id, email, response.body().getId(), tentativas, horaInicial));

                    iv_jogo_nivel_02.setImageResource(response.body().getComponente().getLocalizacao());
                    tv_jogo_nivel_02_slb_01.setText(silabasCorretas[0]);
                    tv_jogo_nivel_02_slb_02.setText(silabasIncorretas[0]);
                    tv_jogo_nivel_02_slb_03.setText(silabasCorretas[1]);
                    tv_jogo_nivel_02_slb_04.setText(silabasIncorretas[1]);
                    tv_jogo_nivel_02_slb_05.setText(silabasCorretas[2]);
                    tv_jogo_nivel_02_slb_06.setText(silabasIncorretas[2]);
                }
            }

            @Override
            public void onFailure(Call<Palavra> call, Throwable t) {
                FalhaRequisicaoDialog("Atenção","Verifique seu status de internet");
            }
        });

        btn_jogo_nivel_02_ajuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int child1 = ll_jogo_nivel_02_encaixe_01.getChildCount();
                int child2 = ll_jogo_nivel_02_encaixe_02.getChildCount();

                if(child1 != 1){
                    ll_jogo_nivel_02_silabas.removeView(tv_jogo_nivel_02_slb_01);
                    ll_jogo_nivel_02_encaixe_01.addView(tv_jogo_nivel_02_slb_01);
                    btn_jogo_nivel_02_ajuda.setEnabled(false);
                    tv_jogo_nivel_02_slb_01.setLongClickable(false);
                }

                else if (child2 != 1) {
                    ll_jogo_nivel_02_silabas.removeView(tv_jogo_nivel_02_slb_03);
                    ll_jogo_nivel_02_encaixe_02.addView(tv_jogo_nivel_02_slb_03);
                    btn_jogo_nivel_02_ajuda.setEnabled(false);
                    tv_jogo_nivel_02_slb_03.setLongClickable(false);
                }

                else {
                    ll_jogo_nivel_02_silabas.removeView(tv_jogo_nivel_02_slb_05);
                    ll_jogo_nivel_02_encaixe_03.addView(tv_jogo_nivel_02_slb_05);
                    btn_jogo_nivel_02_ajuda.setEnabled(false);
                    tv_jogo_nivel_02_slb_05.setLongClickable(false);
                }
            }
        });


    }

    class MyOnLongClickListener implements View.OnLongClickListener{

        @Override
        public boolean onLongClick(View view) {
            TextView viewRecebida = findViewById(view.getId());
            ClipData data = ClipData.newPlainText(viewRecebida.getText(),"text");
            View.DragShadowBuilder sb = new View.DragShadowBuilder(view);
            view.startDragAndDrop(data,sb,view,0);
            view.setVisibility(View.INVISIBLE);
            return true;
        }
    }

    class MyOnDragListenerReceptor implements View.OnDragListener{

        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            int action = dragEvent.getAction();

            switch (action){
                case DragEvent.ACTION_DRAG_STARTED:
                    if(dragEvent.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)){
                        return true;
                    }
                    return false;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_LOCATION:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    LinearLayout container = (LinearLayout) view;
                    if(container.getChildCount() == 1){View v = (View) dragEvent.getLocalState();
                        ViewGroup owner = (ViewGroup) v.getParent();
                        owner.removeView(v);
                        owner.addView(v);
                        v.setVisibility(View.VISIBLE);
                        return false;
                    }
                    else{
                        View v = (View) dragEvent.getLocalState();
                        ViewGroup owner = (ViewGroup) v.getParent();
                        owner.removeView(v);
                        container.addView(v);
                        v.setVisibility(View.VISIBLE);
                        break;
                    }
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
            }
            return true;
        }
    }

    class MyOnDragListenerFrameLayout implements View.OnDragListener{

        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            int action = dragEvent.getAction();

            switch (action){
                case DragEvent.ACTION_DRAG_STARTED:
                    if(dragEvent.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)){
                        return true;
                    }
                    return false;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_LOCATION:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    View v = (View) dragEvent.getLocalState();
                    ViewGroup owner = (ViewGroup) v.getParent();
                    owner.removeView(v);
                    LinearLayout container = findViewById(R.id.ll_jogo_nivel_02_silabas);
                    container.addView(v);
                    v.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
            }
            return true;
        }
    }

    class MyOnClickListenerBtnVerificar implements View.OnClickListener {

        private int IdJogador;
        private String EmailJogador;
        private int IdPalavra;
        private int Tentativas;
        private LocalTime DataInicial;

        final LinearLayout ll_jogo_nivel_02_encaixe_01 = findViewById(R.id.ll_jogo_nivel_02_encaixe_01);
        final LinearLayout ll_jogo_nivel_02_encaixe_02 = findViewById(R.id.ll_jogo_nivel_02_encaixe_02);
        final LinearLayout ll_jogo_nivel_02_encaixe_03 = findViewById(R.id.ll_jogo_nivel_02_encaixe_03);
        final TextView tv_jogo_nivel_02_slb_01 = findViewById(R.id.tv_jogo_nivel_02_slb_01);
        final TextView tv_jogo_nivel_02_slb_03 = findViewById(R.id.tv_jogo_nivel_02_slb_03);
        final TextView tv_jogo_nivel_02_slb_05 = findViewById(R.id.tv_jogo_nivel_02_slb_05);

        public MyOnClickListenerBtnVerificar(int idJogador, String emailJogador, int idPalavra, int tentativas, LocalTime dataInicial){
            this.IdJogador = idJogador;
            this.EmailJogador = emailJogador;
            this.IdPalavra = idPalavra;
            this.Tentativas = tentativas;
            this.DataInicial = dataInicial;
            final Button btn_jogo_nivel_02_desistir = findViewById(R.id.btn_jogo_nivel_02_desistir);
            btn_jogo_nivel_02_desistir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ConfirmarDesistenciaDialog("Atenção", "Deseja realmente desistir", IdJogador, EmailJogador, IdPalavra, DataInicial, Tentativas);
                }
            });
        }

        @Override
        public void onClick(View view) {
            Log.i("id da palavra tbm", String.valueOf(IdPalavra));
            int child1 = ll_jogo_nivel_02_encaixe_01.getChildCount();
            int child2 = ll_jogo_nivel_02_encaixe_02.getChildCount();
            int child3 = ll_jogo_nivel_02_encaixe_03.getChildCount();

            if(child1 == 1 && child2 == 1 && child3 == 1){
                TextView tv1 = (TextView) ll_jogo_nivel_02_encaixe_01.getChildAt(0);
                TextView tv2 = (TextView) ll_jogo_nivel_02_encaixe_02.getChildAt(0);
                TextView tv3 = (TextView) ll_jogo_nivel_02_encaixe_03.getChildAt(0);

                String palavraCorreta = tv_jogo_nivel_02_slb_01.getText().toString() + tv_jogo_nivel_02_slb_03.getText().toString() + tv_jogo_nivel_02_slb_05.getText().toString();
                String valorFinal = tv1.getText().toString() + tv2.getText().toString() + tv3.getText().toString();

                if (valorFinal.equals(palavraCorreta)){
                    LocalTime horaFinal = LocalTime.now();
                    LocalTime duracao = horaFinal.minus(DataInicial.getHour(), ChronoUnit.HOURS);
                    duracao = duracao.minus(DataInicial.getMinute(), ChronoUnit.MINUTES);
                    duracao = duracao.minus(DataInicial.getSecond(), ChronoUnit.SECONDS);
                    DateTimeFormatter formatoDuracao = DateTimeFormatter.ofPattern("HH:mm:ss");
                    DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String duracaoFinal = formatoDuracao.format(duracao);
                    String dataFinal = formatoData.format(LocalDate.now());

                    Partida partidaVitoria = new Partida(1,IdJogador,IdPalavra,dataFinal,duracaoFinal,"v", Tentativas,1);
                    Log.i("Vitória", partidaVitoria.toString());
                    Call<Partida> partidaCall = partidaVitoria.Cadastrar(partidaVitoria);

                    partidaCall.enqueue(new Callback<Partida>() {
                        @Override
                        public void onResponse(Call<Partida> call, Response<Partida> response) {
                            ResultadoDialog("Parabéns", "Você venceu", IdJogador, EmailJogador);
                        }

                        @Override
                        public void onFailure(Call<Partida> call, Throwable t) {
                            FalhaRequisicaoDialog("Atenção", "Não foi possível armazenar informações da sua partida, verifique seu status de internet");
                        }
                    });
                }

                else{
                    if(Tentativas < 9){
                        ErroTentativaDialog("Errou", "Você errou, tente novamente");
                        Tentativas ++;
                        Log.i("tentativas", String.valueOf(Tentativas));
                    }
                    else{
                        Log.i("já era", "perdeu");
                        Tentativas++;

                        LocalTime dataFinal = LocalTime.now();
                        LocalTime duracao = dataFinal.minus(DataInicial.getHour(), ChronoUnit.HOURS);
                        duracao = duracao.minus(DataInicial.getMinute(), ChronoUnit.MINUTES);
                        duracao = duracao.minus(DataInicial.getSecond(), ChronoUnit.SECONDS);
                        DateTimeFormatter formatoDuracao = DateTimeFormatter.ofPattern("HH:mm:ss");
                        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        String duracaoFinal = formatoDuracao.format(duracao);
                        String data = formatoData.format(LocalDate.now());

                        Partida partidaDerrota = new Partida(1,IdJogador,IdPalavra,data,duracaoFinal,"d", Tentativas,0);
                        Log.i("Peredeu", partidaDerrota.toString());
                        Call<Partida> partidaCall = partidaDerrota.Cadastrar(partidaDerrota);

                        partidaCall.enqueue(new Callback<Partida>() {
                            @Override
                            public void onResponse(Call<Partida> call, Response<Partida> response) {
                                ResultadoDialog("Derrota", "Que pena, você perdeu. Tente novamente", IdJogador, EmailJogador);
                            }

                            @Override
                            public void onFailure(Call<Partida> call, Throwable t) {
                                FalhaRequisicaoDialog("Atenção", "Não foi possível armazenar informações da sua partida, verifique seu status de internet");
                            }
                        });
                    }
                }
            }

            Button btn_jogo_nivel_02_desistir = findViewById(R.id.btn_jogo_nivel_02_desistir);
            btn_jogo_nivel_02_desistir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ConfirmarDesistenciaDialog("Atenção", "Deseja realmente desistir", IdJogador, EmailJogador, IdPalavra, DataInicial, Tentativas);
                }
            });
        }
    }

    private void FalhaRequisicaoDialog(String titulo, String mensagem){
        try {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(titulo)
                    .setMessage(mensagem)
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(),Home.class);
                            startActivity(intent);
                        }
                    }).create();
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ErroTentativaDialog(String titulo, String mensagem){
        try {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(titulo)
                    .setMessage(mensagem)
                    .setCancelable(false)
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

    private void ResultadoDialog(String titulo, String mensagem, final int id, final String email){
        try {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(titulo)
                    .setMessage(mensagem)
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(),Home.class);
                            intent.putExtra("nome", id);
                            intent.putExtra("email", email);
                            startActivity(intent);
                        }
                    }).create();
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ConfirmarDesistenciaDialog(String titulo, String mensagem, final int IdJogador, final String EmailJogador, final int IdPalavra, final LocalTime DataInicial, final int Tentativas){
        try {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(titulo)
                    .setMessage(mensagem)
                    .setCancelable(false)
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            LocalTime dataFinal = LocalTime.now();
                            LocalTime duracao = dataFinal.minus(DataInicial.getHour(), ChronoUnit.HOURS);
                            duracao = duracao.minus(DataInicial.getMinute(), ChronoUnit.MINUTES);
                            duracao = duracao.minus(DataInicial.getSecond(), ChronoUnit.SECONDS);
                            DateTimeFormatter formatoDuracao = DateTimeFormatter.ofPattern("HH:mm:ss");
                            DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            String duracaoFinal = formatoDuracao.format(duracao);
                            String data = formatoData.format(LocalDate.now());

                            Partida partidaDesistencia = new Partida(1,IdJogador,IdPalavra,data,duracaoFinal,"d", Tentativas,0);
                            Log.i("Partida desistência", partidaDesistencia.toString());
                            Call<Partida> partidaCall = partidaDesistencia.Cadastrar(partidaDesistencia);

                            partidaCall.enqueue(new Callback<Partida>() {
                                @Override
                                public void onResponse(Call<Partida> call, Response<Partida> response) {
                                    ResultadoDialog("Desistência", "Que pena, você desistiu. Não deixe isso se tornar um hábito, você consegue", IdJogador, EmailJogador);
                                }

                                @Override
                                public void onFailure(Call<Partida> call, Throwable t) {
                                    FalhaRequisicaoDialog("Atenção", "Não foi possível cadastrar sua partida, verifique seu status de internet");
                                }
                            });
                        }
                    })
                    .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .create();
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}