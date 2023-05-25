package com.example.inicio;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.inicio.entidadesRutina.Rutina;

import java.util.ArrayList;

public class bdHelper extends SQLiteOpenHelper {
    //Nombre de la base de datos
    private static final String DATABASE_NAME = "atem.db";
    //Esta normalmente está en 1 y cuando se corre por primera vez se ejecuta el onCreate(), que lo que hace es
    // crear y ejecutar toda esta clase (por ende se crea la base de datos y la tabla).
    // Si queremos que se vuelva a correr este código, ya sea porque creamos otra tabla o agregamos una columna a una tabla,
    // aumentamos la versión (si estaba en 1, ponemos 2 y así) y ahora se ejecutaría el onUprgade
    private static final int DATABASE_VERSION = 1;
    //Nombre de la tabla
    public static final String TABLE_USERS = "usuarios";
    //Nombre de la columna id
    public static final String COLUMN_ID = "id";
    //Nombre de la columna nombre
    public static final String COLUMN_NOMBRE = "nombre";
    //Nombre de la columna username
    public static final String COLUMN_USERNAME = "username";
    //Nombre de la columna correo
    public static final String COLUMN_CORREO = "correo";
    //Nombre de la columna fecha
    public static final String COLUMN_FECHA = "fecha";
    //Nombre de la columna pais
    public static final String COLUMN_PAIS = "pais";
    //Nombre de la columna ciudad
    public static final String COLUMN_CIUDAD = "ciudad";
    //Nombre de la columna password
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_IMG = "img";

    //Nombre de la tabla 2
    public static final String TABLE_HABITS= "habitos";
    //Nombre de la columna id
    public static final String COLUMN_ID_HABITS = "idHabitos";
    //Nombre de la columna nombre
    public static final String COLUMN_HABIT = "habito";
    //Nombre de la columna categoria
    public static final String COLUMN_CATEGORY = "categoria";
    //Nombre de la columna frecuencia
    public static final String COLUMN_TIMES = "frecuencia";
    //Nombre de la columna lunes
    public static final String COLUMN_LUNES = "lunes";
    //Nombre de la columna lunes
    public static final String COLUMN_MARTES = "martes";
    //Nombre de la columna lunes
    public static final String COLUMN_MIERCOLES = "miercoles";
    //Nombre de la columna lunes
    public static final String COLUMN_JUEVES = "jueves";
    //Nombre de la columna lunes
    public static final String COLUMN_VIERNES = "viernes";
    //Nombre de la columna lunes
    public static final String COLUMN_SABADO = "sabado";
    //Nombre de la columna lunes
    public static final String COLUMN_DOMINGO = "domingo";


    public bdHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //Este método solo se corre la primera vez que se ejecuta una instancia.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String[] createTableQuery = {
                "CREATE TABLE " + TABLE_USERS + "(" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NOMBRE + " TEXT,"+
                        COLUMN_USERNAME + " TEXT," +
                        COLUMN_CORREO + " TEXT,"+
                        COLUMN_FECHA + " TEXT, "+
                        COLUMN_PAIS + " TEXT, "+
                        COLUMN_CIUDAD + " TEXT,"+
                        COLUMN_PASSWORD + " TEXT,"+
                        COLUMN_IMG + " TEXT); ",
                "CREATE TABLE " + TABLE_HABITS + "(" +
                        COLUMN_ID_HABITS + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_HABIT + " TEXT,"+
                        COLUMN_CATEGORY + " TEXT," +
                        COLUMN_TIMES + " INTEGER,"+
                        COLUMN_LUNES + " INTEGER, "+
                        COLUMN_MARTES + " INTEGER, "+
                        COLUMN_MIERCOLES + " INTEGER,"+
                        COLUMN_JUEVES + " INTEGER,"+
                        COLUMN_VIERNES + " INTEGER,"+
                        COLUMN_SABADO + " INTEGER,"+
                        COLUMN_DOMINGO+ " INTEGER)"
        };
               for(String createTableQuerys : createTableQuery) {
                   sqLiteDatabase.execSQL(createTableQuerys);
               }

    }
    //Este método se ejecuta si aumentamos el DATABASE_VERSION
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String[] dropTableQuery = {
                "DROP TABLE IF EXISTS " + TABLE_USERS,
                "DROP TABLE IF EXISTS " + TABLE_HABITS,
        };
        for(String dropTableQuerys : dropTableQuery) {
            sqLiteDatabase.execSQL(dropTableQuerys);
        }
        onCreate(sqLiteDatabase);
    }


    public void guardarHabito(String nombre, String categoria, String numDias, String m, String ma, String mier,String juev, String vier, String sab, String dom) {

        SQLiteDatabase db = this.getWritableDatabase();
        String insertQuery = "INSERT INTO " + TABLE_HABITS + " (" +
                COLUMN_HABIT + ", "+
                COLUMN_CATEGORY + ", " +
                COLUMN_TIMES + ", "+
                COLUMN_LUNES + ", "+
                COLUMN_MARTES + ", "+
                COLUMN_MIERCOLES+ ", "+
                COLUMN_JUEVES + ", "+
                COLUMN_VIERNES + ", "+
                COLUMN_SABADO + ", "+
                COLUMN_DOMINGO + ") VALUES ('" +
                nombre + "', '" +
                categoria + "', '"+
                numDias + "', '"+
                m + "', '" +
                ma + "', '"+
                mier + "', '"+
                juev + "', '"+
                vier + "', '"+
                sab + "', '"+
                dom + "')";
        db.execSQL(insertQuery);
        db.close();
    }
 //Metodo para UPDATE database



    //Método para registrar un usuario
    public void signUp(String nombre, String user, String correo, String sfecha, String pais, String ciudad,String pass, String img) {
        SQLiteDatabase db = this.getWritableDatabase();
        String insertQuery = "INSERT INTO " + TABLE_USERS + " (" +
                COLUMN_NOMBRE + ", "+
                COLUMN_USERNAME + ", " +
                COLUMN_CORREO + ", "+
                COLUMN_FECHA + ", "+
                COLUMN_PAIS + ", "+
                COLUMN_CIUDAD+ ", "+
                COLUMN_PASSWORD + ", "+
                COLUMN_IMG + ") VALUES ('" +
                nombre + "', '" +
                user + "', '"+
                correo + "', '"+
                sfecha + "', '" +
                pais + "', '"+
                ciudad + "', '"+
                pass + "', '"+
                img + "')";
        db.execSQL(insertQuery);
        db.close();
    }
    //Método para hacer login
    public boolean signIn(String user, String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS +
                " WHERE " + COLUMN_USERNAME + " ='"+user+"' AND " +
                COLUMN_PASSWORD + " ='"+pass+"'";
        Cursor cursor = db.rawQuery(query, null);
        boolean loginSuccessful = cursor.getCount() > 0;
        Log.d("Database Helper", String.valueOf(loginSuccessful));
        cursor.close();
        db.close();

        return loginSuccessful;

    }
    public Cursor obtenerDatosUsuario(String nombreUsuario) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "id",
                "nombre",
                "username",
                "password",
                "img"
                // ... Agrega aquí el nombre de las columnas que deseas obtener
        };

        String selection = "username=?";
        String[] selectionArgs = {nombreUsuario};

        Cursor cursor = db.query("usuarios", projection, selection, selectionArgs, null, null, null);
        return cursor;
    }


}
