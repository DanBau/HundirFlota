
package com.daniel_bautista.hundirflota.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.daniel_bautista.hundirflota.Clases.Coordenada;
import com.daniel_bautista.hundirflota.Fragments.TableroFrag;
import com.daniel_bautista.hundirflota.R;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    //implementamos los métodos abstractos
    //Necesitamos un contexto, un layout que lo pasamos como referencia y una lista de string
    private Context context;
    private int layout;
    private List<Coordenada> coordenadas;

    public MyAdapter(Context context, int layout, List<Coordenada> coordenadas) {
        this.context = context;
        this.layout = layout;
        this.coordenadas = coordenadas;
    }

    // Le dice al activity cuántas veces hay que iterar sobre un listview
    @Override
    public int getCount() {
        return this.coordenadas.size();
    }

    // Para obtener un item, me devuelve el item de la posicion
    @Override
    public Object getItem(int position) {
        return this.coordenadas.get(position);
    }

    //Para obtener el id de un item
    @Override
    public long getItemId(int id) {
        return id;
    }

    // Donde se dibuja lo que queremos hacer, el método clave
    // es donde está el tema
    // convertView es la lista de vistas que se va a dibujar
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        // Copiamos la vista que vamos a inflar
        View v = convertView;
        String currentPosicion;

        if (v == null) {
            // Usamos la clase LayoutInflater que se obtiene de un método de la misma clase pasándole u contexto
            // Inflamos la vista que nos hallegado con el layout personalizado
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            // Le indicamos el Layout que hemos creado antes
            v = layoutInflater.inflate(this.layout, null);
        }

        // Nos traemos el valor de la posición
        //Esto es si esta en modo debug pongo si es barco b, zona segura zs y agua a.
        if (TableroFrag.gestorTablero.getModoDebug()) {
            if (coordenadas.get(position).getEsBarco() == Coordenada.EsBarco.ZONA_CHOQUE) {
                currentPosicion = "ZS";
            } else if (coordenadas.get(position).getEsBarco() == Coordenada.EsBarco.AGUA){
                currentPosicion = "A";
            } else {
                currentPosicion = "B";
            }

        } else {
            // Si el modo debug esta en falso pongo la coordenada
            currentPosicion = coordenadas.get(position).getPosicion();
        }


        //Aqui cojo los componentes del elemento clicado y los modifico
        TextView txtView = (TextView) v.findViewById(R.id.gvTablero_txtCoordenada);
        ImageView imgV = (ImageView) v.findViewById(R.id.imgBarco);
        ImageView imgA = (ImageView) v.findViewById(R.id.imgAgua);


        //Si selecciona un barco que estaba sin seleccionar pone la imagen de tocado en visible y invisible el texto
        //Si no es barco lo mismo pero pone la imagen del agua

        if (coordenadas.get(position).getEsBarco() == Coordenada.EsBarco.BARCO && coordenadas.get(position).getEstado() == Coordenada.Estado.SELECCIONADO) {
            imgV.setVisibility(View.VISIBLE);
            txtView.setVisibility(View.INVISIBLE);
        } else if ((coordenadas.get(position).getEsBarco() == Coordenada.EsBarco.AGUA || coordenadas.get(position).getEsBarco() == Coordenada.EsBarco.ZONA_CHOQUE) && coordenadas.get(position).getEstado() == Coordenada.Estado.SELECCIONADO) {
            imgA.setVisibility(View.VISIBLE);
            txtView.setVisibility(View.INVISIBLE);
        }


        txtView.setText(currentPosicion);
        // Devolvemos la vista inflada y modificada  para terminar
        return v;
    }

}

