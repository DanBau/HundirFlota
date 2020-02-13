package com.daniel_bautista.hundirflota.Clases;

import android.view.View;
import android.widget.ImageView;

import com.daniel_bautista.hundirflota.Fragments.TableroFrag;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GestorTablero implements Serializable {

    Random rnd = new Random(); //Ramdom para trabajar despues
    public static final int TAM_MAX = 8; // tamaño maximo de coordenada
    public List<Coordenada> CoordenadasList; // Lo que seria el tablero
    public List<Barco> listBarcos; // Conjunto de barcos
    public Boolean ModoDebug; // modoDebug
    public int intentos;

    public Boolean getModoDebug() {
        return ModoDebug;
    }

    public void setModoDebug(Boolean modoDebug) {
        ModoDebug = modoDebug;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }



    public GestorTablero() {
        this.CoordenadasList = new ArrayList<Coordenada>();
        this.listBarcos = new ArrayList<Barco>();
        this.intentos = 1;
        RellenarTablero();
    }

    /**
     * Método para volver a empezar una partida
     */
    public void Reiniciar(){
        this.CoordenadasList.clear();
        this.listBarcos.clear();
        this.intentos = 1;
        RellenarTablero();
    }


    /**
     * Funcion principal que genera el tablero con sus barcos
     */
    public void RellenarTablero() {

        // primero relleno mi lista de de coodenadas con su coordenada 1.1 1.2... 2.1 2.2 etc
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                Coordenada newCr = new Coordenada(i, j);
                this.CoordenadasList.add(newCr);
            }
        }

        // es un aux booleana para saber si el barco entra o no
        Boolean sePuede = false;

        // coloco el barco de 4
        while (!sePuede) {
            sePuede = ColocarBarco(4);
        }


        // coloco el barcos de 3
        // Hago lo mismo que con el 4 pero dentro de un for de 2 ya que son 2 barcos
        sePuede = false;
        for (int i = 0; i < 2; i++) {

            while (!sePuede) {
                sePuede = ColocarBarco(3);
            }
            sePuede = false;
        }

        // coloco el barcos de 2
        // Hago lo mismo que con el 3 pero dentro de un for de 3 ya que son 3 barcos
        for (int j = 0; j < 3; j++) {
            while (!sePuede) {
                sePuede = ColocarBarco(2);
            }
            sePuede = false;
        }


    }


    /**
     * crear la parte de zona segura de cada barco
     * @param cInit  coordena inicial del barco 1 parte
     * @param esVertical direccion del barco
     * @param tamano
     * @return
     */
    private List<Coordenada> RodearBarco(Coordenada cInit, Boolean esVertical, int tamano) {

        //llamo a 4 funciones diferentes para cubrir distintos lados
        List<Coordenada> coorArriba = CubrirArriba(cInit, tamano, esVertical);
        List<Coordenada> coorAbajo = CubrirAbajo(cInit, tamano, esVertical);
        List<Coordenada> coorDer = CubrirDer(cInit, tamano, esVertical);
        List<Coordenada> coorIzq = CubrirIzq(cInit, tamano, esVertical);


        //Y luego las listas de coordenas que devuelven las junto en 1 sola y devuelvo 1 sola lista
        List<Coordenada> coorZChoque = new ArrayList<>();
        coorZChoque.addAll(coorAbajo);
        coorZChoque.addAll(coorArriba);
        coorZChoque.addAll(coorDer);
        coorZChoque.addAll(coorIzq);

        return coorZChoque;

    }


    private List<Coordenada> CubrirIzq(Coordenada coord, int tam, Boolean esVertical) {
        //Cojo la codernada inicial que paso como parametro y se la asignaturo a x e y
        int x, y = 0;
        List<Coordenada> coordenadasIzq = new ArrayList<>();

        //dependiendo la direccion la logica cambia
        // si en vertical para cubrir el lado izquierdo es cojer la cinit x que es lo horizontal restarle 1
        // Y hacer un for dependiendo del tam, añadiendo esas coordenadas
        if (esVertical) {
            x = coord.getPosH() - 1;
            y = coord.getPosAl();
            int altu = y + tam;
            for (int i = y; i < altu; i++) {
                coordenadasIzq.add(new Coordenada(i, x, Coordenada.EsBarco.ZONA_CHOQUE));
            }
        } else {
            // lo mismo que arriba pero cogiendo tanto una pos menos de x como de y
            // para empezar a la izquierda y uno arriba y haces un for de 3 y ya lo cubres
            x = coord.getPosH() - 1;
            y = coord.getPosAl() - 1;
            tam = y + 3;
            for (int i = y; i < tam; i++) {
                coordenadasIzq.add(new Coordenada(i, x, Coordenada.EsBarco.ZONA_CHOQUE));
            }
        }
        return coordenadasIzq;
    }

    private List<Coordenada> CubrirDer(Coordenada coord, int tam, Boolean esVertical) {
        int x, y = 0;
        List<Coordenada> coordenadasDer = new ArrayList<>();

        if (esVertical) {
            x = coord.getPosH() + 1;
            y = coord.getPosAl();
            int altu = y + tam;
            for (int i = y; i < altu; i++) {
                coordenadasDer.add(new Coordenada(i, x, Coordenada.EsBarco.ZONA_CHOQUE));
            }
        } else {
            x = coord.getPosH() + tam;
            y = coord.getPosAl() - 1;
            tam = y + 3;
            for (int i = y; i < tam; i++) {
                coordenadasDer.add(new Coordenada(i, x, Coordenada.EsBarco.ZONA_CHOQUE));
            }
        }
        return coordenadasDer;
    }

    private List<Coordenada> CubrirAbajo(Coordenada coord, int tam, Boolean esVertical) {
        int x, y = 0;
        List<Coordenada> coordenadasAbajo = new ArrayList<>();

        if (esVertical) {
            x = coord.getPosH() - 1;
            y = coord.getPosAl() + tam;
            int dosMas = x + 3;
            for (int i = x; i < dosMas; i++) {
                coordenadasAbajo.add(new Coordenada(y, i, Coordenada.EsBarco.ZONA_CHOQUE));
            }
        } else {
            x = coord.getPosH();
            y = coord.getPosAl() + 1;
            tam += x;
            for (int i = x; i < tam; i++) {
                coordenadasAbajo.add(new Coordenada(y, i, Coordenada.EsBarco.ZONA_CHOQUE));
            }
        }
        return coordenadasAbajo;
    }

    private List<Coordenada> CubrirArriba(Coordenada coord, int tam, boolean esVertical) {
        int x, y = 0;
        List<Coordenada> coordenadasArriba = new ArrayList<>();

        if (esVertical) {
            x = coord.getPosH() - 1;
            y = coord.getPosAl() - 1;
            int dosMas = x + 3;
            for (int i = x; i < dosMas; i++) {
                coordenadasArriba.add(new Coordenada(y, i, Coordenada.EsBarco.ZONA_CHOQUE));
            }
        } else {
            x = coord.getPosH();
            y = coord.getPosAl() - 1;
            tam += x;
            for (int i = x; i < tam; i++) {
                coordenadasArriba.add(new Coordenada(y, i, Coordenada.EsBarco.ZONA_CHOQUE));
            }
        }
        return coordenadasArriba;
    }

    /**
     * funcion de colocar barco
     * @param tam para saber el tamaño
     * @return
     */
    public Boolean ColocarBarco(int tam) {
        //llamo a la funcion para saber si V o H
        Boolean esVertical = RnDireccion();
        //llamo a una funcion q me devuelve una coor inicial
        Coordenada cInit = CoorInit(tam, esVertical);
        List<Coordenada> coordenasBarco = new ArrayList<Coordenada>();

        int ultimo = 0; // ultima coordenada es decir si es cinit 1.1 y es vertical ultimo es 1.4 si es el barco de 4
        if (esVertical) {
            ultimo = cInit.getPosAl() + tam;
            //hago un for de la pos Y a su ultima coordenada
            for (int altura = cInit.getPosAl(); altura < ultimo; altura++) {
                Coordenada newCord = new Coordenada(altura, cInit.getPosH(), Coordenada.EsBarco.BARCO);
                coordenasBarco.add(newCord);
            }
        } else {
            ultimo = cInit.getPosH() + tam;
            //hago un for de la pos Y a su ultima coordenada
            for (int horizontal = cInit.getPosH(); horizontal < ultimo; horizontal++) {
                Coordenada newCord = new Coordenada(cInit.getPosAl(), horizontal, Coordenada.EsBarco.BARCO);
                coordenasBarco.add(newCord);
            }
        }

        //compruebo que esas posiciones no esten ocupadas
        Boolean esPosible = ComprobarEspacio(coordenasBarco);

        if (!esPosible) {
            return false;
        }

        //añado la zona segura
        coordenasBarco.addAll(RodearBarco(cInit, esVertical, tam));

        //añado ami tablero el barco con su zona segura
        for (Coordenada misCoord : this.CoordenadasList) {
            for (Coordenada coordBarco : coordenasBarco) {
                if (coordBarco.equals(misCoord)) {
                    misCoord.setEsBarco(coordBarco.getEsBarco());
                }
            }
        }

        //Añado a mi lista de barco el barco
        listBarcos.add(new Barco(tam, coordenasBarco));
        return true;
    }

    /***
     * Hace un Random para saber que dirección
     * @return devuelve true si es vertical y false si es horizontal
     */
    private Boolean RnDireccion() {
        int dir = rnd.nextInt(2);
        return dir == 1 ? true : false;
    }

    /**
     * devuelvo la coordenada inicial
     * @param tam
     * @param direc
     * @return
     */
    private Coordenada CoorInit(int tam, boolean direc) {
        int posHorizontal, posAltura;
        //si es vertical
        if (direc) {
            //hago un random H de 1 - 8
            posHorizontal = rnd.nextInt(8) + 1;
            // hago un random V de 1 hasta 8 menos el tamaño del barco
            posAltura = rnd.nextInt((8 - tam)) + 1;

        } else {
            // hago un random H de 1 hasta 8 menos el tamaño del barco
            posHorizontal = rnd.nextInt((8 - tam)) + 1;
            //hago un random V de 1 - 8
            posAltura = rnd.nextInt(8) + 1;
        }

        return new Coordenada(posAltura, posHorizontal);
    }

    private Boolean ComprobarEspacio(List<Coordenada> coordenadasBarco) {
        //Hago un for y me dice si una posicion ya es barco o zona segura si lo es devuelve false
        for (Coordenada misCoord : this.CoordenadasList) {
            for (Coordenada coordBarco : coordenadasBarco) {
                if (coordBarco.equals(misCoord)) {
                    if (misCoord.getEsBarco() == Coordenada.EsBarco.BARCO || misCoord.getEsBarco() == Coordenada.EsBarco.ZONA_CHOQUE) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public int ComprobarHundidos(Coordenada coor) {

        //le pasas una coordena de tipo barco mira de que barco es y si todas sus coord estan tocadas devuelve true
        for (Barco barco : this.listBarcos) {
            if (barco.getCoordenadas().contains(coor)) {
                barco.setPosTocados(barco.getPosTocados() + 1);
                if (barco.getLongitud() == barco.getPosTocados()) {
                    barco.setHundido(Boolean.TRUE);
                    return barco.getLongitud();
                }
            }
        }

        return 0;

    }

    /**
     * Devuelve si has ganado
     * @return si un barco se hunde comprueba si es el ultimo
     */
    public boolean HasGanado() {
        for (Barco barco : this.listBarcos) {
            if (barco.getHundido() == Boolean.FALSE){
                return false;
            }
        }
        return true;
    }

}
