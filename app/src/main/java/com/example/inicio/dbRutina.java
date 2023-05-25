package com.example.inicio;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.inicio.entidadesRutina.Rutina;

import java.util.ArrayList;

public class dbRutina extends bdHelper{
    Context context;

    public dbRutina(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public ArrayList<Rutina> mostrarContactos() {

        bdHelper dbHelper = new bdHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Rutina> listaRutina = new ArrayList<>();
        Rutina contacto;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_HABITS + " ORDER BY habito ASC", null);

        if (cursorContactos.moveToFirst()) {
            do {
                contacto = new Rutina();
                contacto.setId(cursorContactos.getInt(0));
                contacto.setHabito(cursorContactos.getString(1));
                contacto.setCategoria(cursorContactos.getString(2));
                contacto.setFrecuencia(cursorContactos.getString(3));
                contacto.setLunes(cursorContactos.getString(4));
                contacto.setMartes(cursorContactos.getString(5));
                contacto.setMiercoles(cursorContactos.getString(6));
                contacto.setJueves(cursorContactos.getString(7));
                contacto.setViernes(cursorContactos.getString(8));
                contacto.setSabado(cursorContactos.getString(9));
                contacto.setDomingo(cursorContactos.getString(10));
                listaRutina.add(contacto);
            } while (cursorContactos.moveToNext());
        }

        cursorContactos.close();

        return listaRutina;
    }
    public Rutina verContacto(int id) {

        bdHelper dbHelper = new bdHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Rutina contacto=null;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_HABITS + " WHERE idHabitos = "+ id +" LIMIT 1", null);

        if (cursorContactos.moveToFirst()) {

                contacto = new Rutina();
                contacto.setId(cursorContactos.getInt(0));
                contacto.setHabito(cursorContactos.getString(1));
                contacto.setCategoria(cursorContactos.getString(2));
                contacto.setFrecuencia(cursorContactos.getString(3));
                contacto.setLunes(cursorContactos.getString(4));
                contacto.setMartes(cursorContactos.getString(5));
                contacto.setMiercoles(cursorContactos.getString(6));
                contacto.setJueves(cursorContactos.getString(7));
                contacto.setViernes(cursorContactos.getString(8));
                contacto.setSabado(cursorContactos.getString(9));
                contacto.setDomingo(cursorContactos.getString(10));
        }

        cursorContactos.close();

        return contacto;
    }



}