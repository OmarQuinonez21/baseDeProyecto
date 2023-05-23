package com.example.inicio;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class bdHelper extends SQLiteOpenHelper {
    //Nombre de la base de datos
    private static final String DATABASE_NAME = "atem.db";
    //Esta normalmente está en 1 y cuando se corre por primera vez se ejecuta el onCreate(), que lo que hace es
    // crear y ejecutar toda esta clase (por ende se crea la base de datos y la tabla).
    // Si queremos que se vuelva a correr este código, ya sea porque creamos otra tabla o agregamos una columna a una tabla,
    // aumentamos la versión (si estaba en 1, ponemos 2 y así) y ahora se ejecutaría el onUprgade
    private static final int DATABASE_VERSION = 4;
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


    public bdHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //Este método solo se corre la primera vez que se ejecuta una instancia.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOMBRE + " TEXT,"+
                COLUMN_USERNAME + " TEXT," +
                COLUMN_CORREO + " TEXT,"+
                COLUMN_FECHA + " TEXT, "+
                COLUMN_PAIS + " TEXT, "+
                COLUMN_CIUDAD + " TEXT,"+
                COLUMN_PASSWORD + " TEXT,"+
                COLUMN_IMG + " TEXT)";
        sqLiteDatabase.execSQL(createTableQuery);
    }
    //Este método se ejecuta si aumentamos el DATABASE_VERSION
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_USERS;
        sqLiteDatabase.execSQL(dropTableQuery);
        onCreate(sqLiteDatabase);
    }
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
}
