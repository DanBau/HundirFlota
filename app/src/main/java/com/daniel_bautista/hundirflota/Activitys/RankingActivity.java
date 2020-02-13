package com.daniel_bautista.hundirflota.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.daniel_bautista.hundirflota.Adapters.AdapterMarcador;
import com.daniel_bautista.hundirflota.Clases.BaseDatos;
import com.daniel_bautista.hundirflota.Clases.Partida;
import com.daniel_bautista.hundirflota.R;

import static com.daniel_bautista.hundirflota.Activitys.MainActivity.datos;
import static com.daniel_bautista.hundirflota.Activitys.MainActivity.db;
import static com.daniel_bautista.hundirflota.Activitys.MainActivity.gestorPartidas;
import static com.daniel_bautista.hundirflota.Fragments.TableroFrag.gestorTablero;

public class RankingActivity extends AppCompatActivity {

    public static AdapterMarcador adapterMarcador;
    private ListView lvMarcador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        lvMarcador = (ListView) findViewById(R.id.lvRanking);
        mostrarMarcador();
        llamarAdapter();

    }


    public void onResume() {
        super.onResume();
        llamarAdapter();
        datos = new BaseDatos(this);
        db = datos.getWritableDatabase();
    }

    public void onPause() {
        super.onPause();
        db.close();
    }


    /***
     * Función para mostrar el lisw de marcadores
     * @return Devuelve un mens de error si algo a ido mal y " " si todo a ido bien
     */
    public static String mostrarMarcador() {
        db = datos.getWritableDatabase();
        try {
            if (db != null) {
                gestorPartidas.PartidasList.clear();
                //Select para ordenar primero por intentos y despues por tiempo
                Cursor cursor = db.rawQuery("SELECT * FROM Marcador ORDER BY intentos, tiempo", null);

                if (cursor.moveToFirst()) {
                    do {
                        gestorPartidas.PartidasList.add(new Partida(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3)));
                    } while (cursor.moveToNext());
                }
                db.close();
                return "";
            } else {
                db.close();
                return "Error al acceder a la base de datos";
            }
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    //Llama al adapter necesario
    public void llamarAdapter() {
        adapterMarcador = new AdapterMarcador(this, R.layout.lv_ranking, gestorPartidas.PartidasList); //Llama al adapter
        lvMarcador.setAdapter(adapterMarcador);
        registerForContextMenu(lvMarcador);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
