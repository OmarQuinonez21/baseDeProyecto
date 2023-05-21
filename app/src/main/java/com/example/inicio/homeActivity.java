package com.example.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class homeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide(); //esconde el titulo de la app para usar toda la pantalla
    }
}