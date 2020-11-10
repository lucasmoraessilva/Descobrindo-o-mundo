package com.lighttech.descobrindoomundo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class Home extends AppCompatActivity {


    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button btn_home_jogar = findViewById(R.id.btn_home_jogar);



        //Intent intent = getIntent();
        final int id = 2; //intent.getStringExtra("nome");
        final String email = "teste@teste.com"; //intent.getStringExtra("email");
        TextView tvTexto = findViewById(R.id.tvTexto);
        tvTexto.setText(String.valueOf(id) + " " + email);
        
        btn_home_jogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NivelDialog("Escolha um nível",id,email);
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