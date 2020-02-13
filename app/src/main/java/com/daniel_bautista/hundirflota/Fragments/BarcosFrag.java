package com.daniel_bautista.hundirflota.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.daniel_bautista.hundirflota.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarcosFrag extends Fragment {

    ImageView imgB4, imgB31, imgB32, imgB21, imgB22, imgB23;
    TextView txtIntentos;


    public BarcosFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_barcos, container, false);
        imgB4 = (ImageView) view.findViewById(R.id.img4);
        imgB31 = (ImageView) view.findViewById(R.id.img31);
        imgB32= (ImageView) view.findViewById(R.id.img32);
        imgB21 = (ImageView) view.findViewById(R.id.img21);
        imgB22 = (ImageView) view.findViewById(R.id.img22);
        imgB23 = (ImageView) view.findViewById(R.id.img23);
        txtIntentos = (TextView) view.findViewById(R.id.txtNumIntentos);
        // Inflate the layout for this fragment
        return view;
    }

    /**
     * cada vez que doy click a una casilla
     * @param barco Si es 0 es agua si no es barco
     * @param intentos numero de intentos
     */
    public void renderText(int barco, int intentos) {

        //actualiza los intentos
        txtIntentos.setText(String.valueOf(intentos));

        //dependiendo de que numero de barco sea pone visible las X del barco correspondiente

        if (barco == 4){
            imgB4.setVisibility(View.VISIBLE);
            return;
        }

        if (barco == 3 && imgB31.getVisibility() == View.INVISIBLE){
            imgB31.setVisibility(View.VISIBLE);
            return;
        }

        if (barco == 3 && imgB31.getVisibility() == View.VISIBLE){
            imgB32.setVisibility(View.VISIBLE);
            return;
        }

        if (barco == 2 && imgB21.getVisibility() == View.INVISIBLE){
            imgB21.setVisibility(View.VISIBLE);
            return;
        }

        if (barco == 2 && imgB21.getVisibility() == View.VISIBLE && imgB22.getVisibility() == View.INVISIBLE){
            imgB22.setVisibility(View.VISIBLE);
            return;
        }

        if (barco == 2 && imgB21.getVisibility() == View.VISIBLE && imgB22.getVisibility() == View.VISIBLE ){
            imgB23.setVisibility(View.VISIBLE);
            return;
        }

    }
}
