package com.example.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class usuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        getSupportActionBar().hide(); //esconde el titulo de la app para usar toda la pantalla

    }
}