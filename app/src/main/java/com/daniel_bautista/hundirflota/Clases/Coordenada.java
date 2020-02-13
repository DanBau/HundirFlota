package com.daniel_bautista.hundirflota.Clases;

import java.util.Objects;

public class Coordenada {

    /**
     * Enumerado para saber si la casilla ya ha sido seleccionada
     */
    public enum Estado {
        SELECCIONADO,
        SIN_SELECCIONAR
    }

    /**
     * Para saber que tipo de coordenada es
     */
    public enum EsBarco {
        AGUA,
        BARCO,
        ZONA_CHOQUE
    }

    private int posH; //Posicion Horizontal
    private int posAl; //Posicion vertical
    private String posicion; // coordenada x.y
    private Estado estado;
    private EsBarco esBarco;

    public Coordenada() {

    }

    public Coordenada(int posAl, int posH) {
        this.posH = posH;
        this.posAl = posAl;
        this.estado = Estado.SIN_SELECCIONAR;
        this.esBarco = EsBarco.AGUA;
        namePosicion();
    }

    public Coordenada(int posAl, int posH, EsBarco esBarco) {
        this.posH = posH;
        this.posAl = posAl;
        this.esBarco = esBarco;
        this.estado = Estado.SIN_SELECCIONAR;
        namePosicion();
    }

    public String getPosicion() {
        return posicion;
    }


    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getPosH() {
        return posH;
    }

    public void setPosH(int posH) {
        this.posH = posH;
    }

    public int getPosAl() {
        return posAl;
    }

    public void setPosAl(int posAl) {
        this.posAl = posAl;
    }

    public EsBarco getEsBarco() {
        return esBarco;
    }

    public void setEsBarco(EsBarco esBarco) {
        this.esBarco = esBarco;
    }

    public void namePosicion(){
        this.posicion = this.posAl +"." + this.posH;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordenada)) return false;
        Coordenada that = (Coordenada) o;
        return getPosH() == that.getPosH() &&
                getPosAl() == that.getPosAl();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosH(), getPosAl());
    }



}
