package com.daniel_bautista.hundirflota.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daniel_bautista.hundirflota.Clases.BaseDatos;
import com.daniel_bautista.hundirflota.Clases.Partida;
import com.daniel_bautista.hundirflota.R;

import static com.daniel_bautista.hundirflota.Activitys.MainActivity.datos;
import static com.daniel_bautista.hundirflota.Activitys.MainActivity.db;
import static com.daniel_bautista.hundirflota.Activitys.MainActivity.gestorPartidas;


public class GanadorActivity extends AppCompatActivity {

    private Partida partida;
    private EditText nombreGanador;
    private TextView txtIntentos, txtTime;
    private Button btnGuardar, btnVolver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganador);

        btnGuardar = (Button) findViewById(R.id.gan_btnGuardar);
        btnVolver = (Button) findViewById(R.id.gan_btnVolver);
        nombreGanador = (EditText) findViewById(R.id.gan_txtganadorNombre);
        txtIntentos = (TextView) findViewById(R.id.gan_txtIntentos);
        txtTime = (TextView) findViewById(R.id.gan_txtTiempo);

        //Coje los valores de la partida que se los pasa el fragmento de Jugar si ha hundido todos los barcos.
        //Que son el tiempo y los intentos en los que lo ha hecho
        Bundle bundle = getIntent().getExtras();
        partida = (Partida) bundle.get("partida");

        txtIntentos.setText("Intentos: "+ String.valueOf(partida.getIntentos()));
        txtTime.setText("Tiempo: "+ partida.getTiempo());

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        /***
         * Este método sirve para guardar la partida si en edit text esta vacio lo guarda como anonimo
         */
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombreJu = nombreGanador.getText().toString();

                if (nombreJu.isEmpty()) {
                    addMarcador("Anónimo", partida.getIntentos(), partida.getTiempo());
                } else {
                    addMarcador(nombreJu, partida.getIntentos(), partida.getTiempo());
                }

            }
        });

    }

    public void Salir() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /***
     * Metodo para guardar tanto en la base de datos como en la lista el marcador
     * @param nombre Nombre del jugador
     * @param intentos Intentos en los que consiguio ganar
     * @param tiempo Tiempo que tardo
     */
    public void addMarcador(String nombre, int intentos, String tiempo) {
        if (db != null) {
            ContentValues registro = new ContentValues();
            //Compruebo que el nombre si lo ponen vacio
            if (nombre.isEmpty()) {
                registro.put("intentos", intentos);
                registro.put("tiempo", tiempo);
            } else {
                registro.put("nombreJugador", nombre);
                registro.put("intentos", intentos);
                registro.put("tiempo", tiempo);
            }

            if (db.insert("Marcador", null, registro) == -1) {
                db.close();
                Toast.makeText(this, "Error al añadir el contacto", Toast.LENGTH_LONG).show();
            } else {
                gestorPartidas.PartidasList.add(new Partida(gestorPartidas.PartidasList.size(), nombre, intentos, tiempo));
                //adapterIntentos.notifyDataSetChanged();
                Intent intent = new Intent(this, RankingActivity.class);
                startActivity(intent);
            }
        } else {
            db.close();
            Toast.makeText(this, "Error al acceder a la base de datos", Toast.LENGTH_LONG).show();
        }
    }


    /***
     * Esto es para asegurarme que no quiere guardar la partida
     */
    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("¿Seguro que no quieres guardar la partida ?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                Salir();
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                return;
            }
        });
        dialogo1.show();
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
