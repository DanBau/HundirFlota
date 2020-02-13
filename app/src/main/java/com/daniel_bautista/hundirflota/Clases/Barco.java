package com.daniel_bautista.hundirflota.Clases;

import java.util.List;

public class Barco {

    private int Longitud; // Tamaño del barco
    private List<Coordenada> coordenadas; //Cada barco tiene una lista de coordenadas
    private int posTocados; //numero de coordenadas del barco tocadas
    private Boolean Hundido;//Si esta hundido o no

    public Barco() {
    }


    public Barco(int longitud, List<Coordenada> coordenadas) {
        Longitud = longitud;
        this.coordenadas = coordenadas;
        this.posTocados = 0;
        this.Hundido = false;
    }

    public Boolean getHundido() {
        return Hundido;
    }

    public void setHundido(Boolean hundido) {
        Hundido = hundido;
    }

    public int getLongitud() {
        return Longitud;
    }

    public void setLongitud(int longitud) {
        Longitud = longitud;
    }

    public List<Coordenada> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(List<Coordenada> coordenadas) {
        this.coordenadas = coordenadas;
    }

    public int getPosTocados() {
        return posTocados;
    }

    public void setPosTocados(int posTocados) {
        this.posTocados = posTocados;
    }
}
