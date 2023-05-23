package com.example.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class habitosActivity extends AppCompatActivity {

    ImageButton btnRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitos);
        getSupportActionBar().hide(); //esconde el titulo de la app para usar toda la pantalla
        btnRegresar=(ImageButton)findViewById(R.id.regreso_pomo);

        btnRegresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onBackPressed();
            }
        });
    }
}