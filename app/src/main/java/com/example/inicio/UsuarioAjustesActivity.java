package com.example.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class UsuarioAjustesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_ajustes);
        getSupportActionBar().hide();
    }
}