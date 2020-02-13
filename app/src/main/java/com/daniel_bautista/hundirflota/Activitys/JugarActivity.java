package com.daniel_bautista.hundirflota.Activitys;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.daniel_bautista.hundirflota.Fragments.BarcosFrag;
import com.daniel_bautista.hundirflota.Fragments.TableroFrag;
import com.daniel_bautista.hundirflota.R;

public class JugarActivity extends FragmentActivity implements TableroFrag.DataListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);


        //Modo Debug --> Falso no se ve, True si se ve
        TableroFrag.gestorTablero.setModoDebug(false);

    }

    /**
     * Aqui cojo los datos del ultimo click que ha hecho para pasarselos al otro fragmento
     *
     * @param barco    es el tamaño del barco que has pulsado si es o es agua
     * @param intentos en el intento que vas
     */
    @Override
    public void sendData(int barco, int intentos) {
        BarcosFrag barcosFrag = (BarcosFrag) getSupportFragmentManager().findFragmentById(R.id.frag_barcos);
        barcosFrag.renderText(barco, intentos);
    }


    /**
     * Asegurar que desea terminar la partida
     */
    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("¿ Desea terminar con la partida ?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                TableroFrag.gestorTablero.Reiniciar();
                finish();
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                return;
            }
        });
        dialogo1.show();
    }

}
