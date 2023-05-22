package com.example.inicio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class bdHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "prueba.db";
    private static final int DATABASE_VERSION = 3;
    public static final String TABLE_USERS = "usuarios";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";


    public bdHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)";
        sqLiteDatabase.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_USERS;
        sqLiteDatabase.execSQL(dropTableQuery);
        onCreate(sqLiteDatabase);
    }
    public void insertUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String insertQuery = "INSERT INTO " + TABLE_USERS + " (" +
                COLUMN_USERNAME + ", " +
                COLUMN_PASSWORD + ") VALUES ('" +
                username + "', '" +
                password + "')";
        db.execSQL(insertQuery);
        db.close();
    }
    public boolean signIn(String user, String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS +
                " WHERE " + COLUMN_USERNAME + " ='"+user+"' AND " +
                COLUMN_PASSWORD + " ='"+pass+"'";
        Cursor cursor = db.rawQuery(query, null);
        boolean loginSuccessful = cursor.getCount() > 0;
        Log.d("Database Hekper", String.valueOf(loginSuccessful));
        cursor.close();
        db.close();

        return loginSuccessful;

    }
}
