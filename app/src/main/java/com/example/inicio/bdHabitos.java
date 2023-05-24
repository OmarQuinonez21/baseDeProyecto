package com.example.inicio;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;


import com.example.inicio.entidades.habitos;

import java.util.ArrayList;
import java.util.Calendar;

public class bdHabitos extends bdHelper {

    Context context;

    public bdHabitos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public ArrayList<habitos> mostrarHabitos() {

        bdHelper dbHelper = new bdHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<habitos> listaHabitos = new ArrayList<>();
        habitos habito = null;
        Cursor cursorHabitos = null;

        cursorHabitos = db.rawQuery("SELECT habito,categoria, frecuencia FROM habitos", null);

        if (cursorHabitos.moveToFirst()) {
            do {
                habito = new habitos();
                habito.setNombre(cursorHabitos.getString(0));
                habito.setCat(cursorHabitos.getString(1));
                habito.setRep(cursorHabitos.getInt(2));
                listaHabitos.add(habito);
            } while (cursorHabitos.moveToNext());
        }
        cursorHabitos.close();

        return listaHabitos;
    }
}

