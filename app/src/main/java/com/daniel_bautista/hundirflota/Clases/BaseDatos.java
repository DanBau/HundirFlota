package com.daniel_bautista.hundirflota.Clases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {

    private static final int VERSION_BASEDATOS = 1;
    private static final String NOMBRE_BASEDATOS = "muertosHeridosDB.db";
    private static  final String TABLA_MARCADOR = "CREATE TABLE Marcador(id INTEGER PRIMARY KEY AUTOINCREMENT,nombreJugador TEXT NOT NULL DEFAULT 'Anonimo'," +
            "intentos INTEGER NOT NULL DEFAULT (1), tiempo TEXT NOT NULL DEFAULT(0))";

    /***
     * Inicializo todos los propiedades para que en el programa tenga menos intencion de error
     * @param contexto activity que se vaya a utilizar
     */
    public BaseDatos(Context contexto)
    {
        super(contexto,NOMBRE_BASEDATOS,null,VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_MARCADOR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Marcador");
        db.execSQL(TABLA_MARCADOR);
    }
}
