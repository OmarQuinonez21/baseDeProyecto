package com.example.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IniciarSesionActivity extends AppCompatActivity {

    Button btnLogin;
    Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        getSupportActionBar().hide();
        btnLogin=findViewById(R.id.log);
        btnReg=findViewById(R.id.reg);


        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                 Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                 startActivity(intent);
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),RegistroActivity.class);
                startActivity(intent);
            }
        });
    }



}