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
                contacto.setLunes(cursorContactos.getString(2));
                contacto.setMartes(cursorContactos.getString(3));
                contacto.setMiercoles(cursorContactos.getString(4));
                contacto.setJueves(cursorContactos.getString(5));
                contacto.setViernes(cursorContactos.getString(6));
                contacto.setSabado(cursorContactos.getString(7));
                contacto.setDomingo(cursorContactos.getString(8));
                listaRutina.add(contacto);
            } while (cursorContactos.moveToNext());
        }

        cursorContactos.close();

        return listaRutina;
    }

    public Rutina verContacto(int id) {

        bdHelper dbHelper = new bdHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Rutina contacto = null;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_HABITS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorContactos.moveToFirst()) {
            contacto = new Rutina();
            contacto.setId(cursorContactos.getInt(0));
            contacto.setHabito(cursorContactos.getString(1));
            contacto.setLunes(cursorContactos.getString(2));
            contacto.setMartes(cursorContactos.getString(3));
            contacto.setMiercoles(cursorContactos.getString(4));
            contacto.setJueves(cursorContactos.getString(5));
            contacto.setViernes(cursorContactos.getString(6));
            contacto.setSabado(cursorContactos.getString(7));
            contacto.setDomingo(cursorContactos.getString(8));
        }

        cursorContactos.close();

        return contacto;
    }

}