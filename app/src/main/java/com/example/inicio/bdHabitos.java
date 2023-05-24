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
        final Calendar c = Calendar.getInstance();

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        String vdias = "";

        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                vdias = "domingo";
                break;
            case Calendar.MONDAY:
                vdias = "lunes";
                break;
            case Calendar.TUESDAY:
                vdias = "martes";
                break;
            case Calendar.WEDNESDAY:
                vdias = "miercoles";
                break;
            case Calendar.THURSDAY:
                vdias = "jueves";
                break;
            case Calendar.FRIDAY:
                vdias = "viernes";
                break;
            case Calendar.SATURDAY:
                vdias = "sabado";
                break;
        }
        bdHelper dbHelper = new bdHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<habitos> listaHabitos = new ArrayList<>();
        habitos habito = null;
        Cursor cursorHabitos = null;

        cursorHabitos = db.rawQuery("SELECT * FROM " + TABLE_HABITS + " where miercoles = 1", null);

        if (cursorHabitos.moveToFirst()) {
            do {
                habito = new habitos();
                habito.setId(cursorHabitos.getInt(0));
                habito.setNombre(cursorHabitos.getString(1));
                habito.setCat(cursorHabitos.getString(2));
                habito.setRep(cursorHabitos.getInt(3));
                listaHabitos.add(habito);
            } while (cursorHabitos.moveToNext());
        }
        cursorHabitos.close();
        return listaHabitos;
    }
}

