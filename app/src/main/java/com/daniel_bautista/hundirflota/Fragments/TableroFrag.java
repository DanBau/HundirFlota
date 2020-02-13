package com.daniel_bautista.hundirflota.Fragments;


import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.daniel_bautista.hundirflota.Activitys.GanadorActivity;
import com.daniel_bautista.hundirflota.Activitys.PerdedorActivity;
import com.daniel_bautista.hundirflota.Adapters.MyAdapter;
import com.daniel_bautista.hundirflota.Clases.Coordenada;
import com.daniel_bautista.hundirflota.Clases.GestorTablero;
import com.daniel_bautista.hundirflota.Clases.Partida;
import com.daniel_bautista.hundirflota.R;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class TableroFrag extends Fragment {

    private GridView gv_Tablero;
    public static MyAdapter myAdapter;
    public static GestorTablero gestorTablero = new GestorTablero();
    private TextView txtCoor;
    private DataListener callback;
    private Boolean HasGanado = Boolean.FALSE;
    private Date tComienzo, tFinal; // para saber cuando empieza y acaba la partida
    private int init = 0; // numero de clicks que lleva

    public TableroFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tablero, container, false);
        gestorTablero.Reiniciar();
        gv_Tablero = (GridView) view.findViewById(R.id.gv_tablero);
        LlamarAdapter();
        // Inflate the layout for this fragment
        return view;
    }

    public void LlamarAdapter() {
        myAdapter = new MyAdapter(getActivity(),R.layout.gv_tablero, gestorTablero.CoordenadasList);
        gv_Tablero.setAdapter(myAdapter);
        gv_Tablero.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                init ++;

                //Coordenada que se ha pulsado
                Coordenada coorPulsada = gestorTablero.CoordenadasList.get(i);
                final MediaPlayer mp3Hundido = MediaPlayer.create(getActivity(),R.raw.hundido);
                final MediaPlayer mp3Tocado = MediaPlayer.create(getActivity(),R.raw.tocado);
                final MediaPlayer mp3Agua = MediaPlayer.create(getActivity(),R.raw.agua);

                if (init == 1){
                    tComienzo = new Date();
                }

                //si esta sin seleccionar y es distinto q barco aumento los intento
                if (coorPulsada.getEstado()== Coordenada.Estado.SIN_SELECCIONAR && coorPulsada.getEsBarco() != Coordenada.EsBarco.BARCO){
                    gestorTablero.setIntentos(gestorTablero.getIntentos()+1);
                    if (gestorTablero.getIntentos() == 40){
                        tFinal = new Date();
                        Intent intent = new Intent(getActivity(), PerdedorActivity.class);
                        startActivity(intent);
                    }
                }

                // dependiendo si es agua o barco suena una cosa u otra
                if(coorPulsada.getEsBarco()== Coordenada.EsBarco.BARCO && coorPulsada.getEstado()== Coordenada.Estado.SIN_SELECCIONAR){
                    mp3Tocado.start();
                } else if ((coorPulsada.getEsBarco()== Coordenada.EsBarco.AGUA || coorPulsada.getEsBarco()== Coordenada.EsBarco.ZONA_CHOQUE) && coorPulsada.getEstado()== Coordenada.Estado.SIN_SELECCIONAR) {
                    mp3Agua.start();
                }


                int bHundido = 0 ;
                //is barco y no ha sido seleccionado compruebo q no este hundido
                if (coorPulsada.getEsBarco() == Coordenada.EsBarco.BARCO && coorPulsada.getEstado()== Coordenada.Estado.SIN_SELECCIONAR ){
                     bHundido = gestorTablero.ComprobarHundidos(coorPulsada);
                }

                //si hay hundes un barco suena otra musica
                if (bHundido != 0 ){
                    mp3Hundido.start();
                    // comprueba que si has ganado o no
                    HasGanado = gestorTablero.HasGanado();
                    if (HasGanado){
                        //si has ganado se termina el tiempo
                        tFinal = new Date();
                        Partida partida = new Partida(tComienzo,tFinal, gestorTablero.getIntentos());
                        Intent intent = new Intent(getActivity(), GanadorActivity.class);
                        intent.putExtra("partida",partida);
                        startActivity(intent);
                    }
                }

                // En el callback mando tanto el tam del barco o agua y los intentos
                callback.sendData(bHundido, gestorTablero.getIntentos());

                //pongo la coorde en seleccionasa
                coorPulsada.setEstado(Coordenada.Estado.SELECCIONADO);
                registerForContextMenu(gv_Tablero);
                LlamarAdapter();
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            // Convertimos el contexto en DataListener y lo guardamos en el callback
            callback = (DataListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + " DataListener mal implementado");
        }

    }

    public interface DataListener {
        void sendData(int barco, int intentos);
    }

}
