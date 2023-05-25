package com.example.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.inicio.entidadesRutina.Rutina;

public class VerActivity extends AppCompatActivity {

    EditText txtHabito, txtFrecuencia;
    Spinner txtCategorias;
    Button btnEditar;
    String nombreRutina;
    ImageView btnAtrasVer;

    Rutina rutina;
    int id = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
        getSupportActionBar().hide(); //esconde el titulo de la app para usar toda la pantalla
        btnAtrasVer = (ImageView) findViewById(R.id.imgV_btnAtrasVer);
        //Aqui yahel puede agregar intent para volver de la vista editar
        txtHabito = findViewById(R.id.txtHabito);
        txtFrecuencia = findViewById(R.id.txtFrecuencia);
        btnEditar = findViewById(R.id.btn_habitoEditar);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("IdHabitos");
            }
        }else{
            id =(int) savedInstanceState.getSerializable("IdHabitos");
        }
        dbRutina dbContactos = new dbRutina(VerActivity.this);
        rutina = dbContactos.verContacto(id);

        if(rutina != null){
            txtHabito.setText(rutina.getHabito());

        }

    }
}