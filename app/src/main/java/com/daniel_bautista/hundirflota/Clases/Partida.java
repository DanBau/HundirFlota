package com.daniel_bautista.hundirflota.Clases;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Partida implements Serializable {
    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private int id;
    private String Nombre;
    private Date HoraComienzo;
    private Date HoraFinal;
    private int Intentos;
    private String Tiempo;

    public Date getHoraFinal() {
        return HoraFinal;
    }

    public void setHoraFinal(Date horaFinal) {
        HoraFinal = horaFinal;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Date getHoraComienzo() {
        return HoraComienzo;
    }

    public void setHoraComienzo(Date horaComienzo) {
        HoraComienzo = horaComienzo;
    }

    public int getIntentos() {
        return Intentos;
    }

    public void setIntentos(int intentos) {
        Intentos = intentos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTiempo() {
        return Tiempo;
    }

    public void setTiempo(String tiempo) {
        Tiempo = tiempo;
    }

    public Partida() {

    }

    public Partida(String nombre, Date horaComienzo, int intentos) {
        Nombre = nombre;
        HoraComienzo = horaComienzo;
        Intentos = intentos;
    }

    public Partida(Date horaComienzo, Date horaFinal, int intentos) {
        HoraComienzo = horaComienzo;
        HoraFinal = horaFinal;
        Intentos = intentos;
        this.Tiempo = MostrarTiempo();
    }

    public Partida(int id, String nombre, int intentos, String tiempo) {
        this.id = id;
        Nombre = nombre;
        Intentos = intentos;
        Tiempo = tiempo;
    }

    private String MostrarTiempo(){
        return dateFormat.format(this.getHoraFinal().getTime() - this.getHoraComienzo().getTime());
    }

}
