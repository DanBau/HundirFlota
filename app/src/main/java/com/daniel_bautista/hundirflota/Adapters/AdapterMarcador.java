package com.daniel_bautista.hundirflota.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daniel_bautista.hundirflota.Clases.Partida;
import com.daniel_bautista.hundirflota.R;

import java.util.List;

/***
 * Este Adapter es para el listView de Marcadores ya que es diferente al de Intentos
 */
public class AdapterMarcador extends BaseAdapter {
    //implementamos los métodos abstractos
    //Necesitamos un contexto, un layout que lo pasamos como referencia y una lista de string
    private Context context;
    private int layout;
    private List<Partida> partidas;
    private int Posicion;

    public AdapterMarcador(Context context, int layout, List<Partida> partidas) {
        this.context = context;
        this.layout = layout;
        this.partidas = partidas;
    }

    // Le dice al activity cuántas veces hay que iterar sobre un listview
    @Override
    public int getCount() {
        return this.partidas.size();
    }

    // Para obtener un item, me devuelve el item de la posicion
    @Override
    public Object getItem(int position) {
        return this.partidas.get(position);
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
        Posicion ++;
        // Copiamos la vista que vamos a inflar
        View v = convertView;

        if (v == null){
            // Usamos la clase LayoutInflater que se obtiene de un método de la misma clase pasándole u contexto
            // Inflamos la vista que nos hallegado con el layout personalizado
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            // Le indicamos el Layout que hemos creado antes
            v = layoutInflater.inflate(this.layout, null);
        }


        // Nos traemos el valor de la posición
        String currentNombre = partidas.get(position).getNombre();
        int currentIntentos = partidas.get(position).getIntentos();
        String currentTiempo = partidas.get(position).getTiempo();

        // Nos falta rellenar el textView del Layout
        // aquí no tenemos el FindViewById, para referenciarlo tenemos que usar la vista que hemos creado
        TextView txtNombre = (TextView) v.findViewById(R.id.lvR_Nombre);
        TextView txtIntentos = (TextView) v.findViewById(R.id.lvR_Intentos);
        TextView txtTiempo = (TextView) v.findViewById(R.id.lvR_Tiempo);
        TextView txtId = (TextView) v.findViewById(R.id.lvR_Pos);

        txtNombre.setText(currentNombre);
        txtIntentos.setText(String.valueOf(currentIntentos));
        txtId.setText(String.valueOf(Posicion));
        txtTiempo.setText(currentTiempo);

        // Devolvemos la vista inflada y modificada  para terminar
        return v;
    }
}
