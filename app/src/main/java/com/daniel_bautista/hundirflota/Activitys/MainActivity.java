package com.daniel_bautista.hundirflota.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.daniel_bautista.hundirflota.Clases.BaseDatos;
import com.daniel_bautista.hundirflota.Clases.GestorPartidas;
import com.daniel_bautista.hundirflota.R;

public class MainActivity extends AppCompatActivity {

    private Button btnJugar, btnAyuda, btnAcerca, btnRanking;
    public static BaseDatos datos;
    public static SQLiteDatabase db;
    public static GestorPartidas gestorPartidas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gestorPartidas = new GestorPartidas();
        datos = new BaseDatos(this);
        btnJugar = (Button) findViewById(R.id.main_btnJugar);
        btnRanking = (Button) findViewById(R.id.main_btnRanking);
        btnAyuda = (Button) findViewById(R.id.main_btnAyuda);
        btnAcerca = (Button) findViewById(R.id.main_btnAcercaDe);

        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent(MainActivity.this, JugarActivity.class);
                startActivity(resultIntent);
            }
        });

        btnRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent(MainActivity.this, RankingActivity.class);
                startActivity(resultIntent);
            }
        });

        btnAcerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent(MainActivity.this, AcercaActivity.class);
                startActivity(resultIntent);
            }
        });

        btnAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent(MainActivity.this, AyudaActivity.class);
                startActivity(resultIntent);
            }
        });

    }

    public void onResume() {
        super.onResume();
        datos = new BaseDatos(this);
        db = datos.getWritableDatabase();
    }

    public void onPause() {
        super.onPause();
        db.close();
    }
}
