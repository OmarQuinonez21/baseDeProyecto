package com.example.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.inicio.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RutinaActivity extends AppCompatActivity {
    String nombreUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nombreUsuario = getIntent().getStringExtra("usuario");

        setContentView(R.layout.activity_rutina);
        getSupportActionBar().hide(); //esconde el titulo de la app para usar toda la pantalla
        BottomNavigationView navRutina = findViewById(R.id.bottomNavigationView);
        navRutina.setSelectedItemId(R.id.navigation_rut);
        navRutina.setBackground(null);

        navRutina.setOnItemSelectedListener(item->{
            if(item.getItemId()==R.id.navigation_pomo){
                finish();
                Intent intent = new Intent(getApplicationContext(), pomodoroActivity.class);
                intent.putExtra("usuario",nombreUsuario);
                startActivity(intent);
                return true;
            }else if (item.getItemId()==R.id.navigation_home) {
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("usuario",nombreUsuario);
                startActivity(intent);
                return true;
            }

            else if (item.getItemId()==R.id.navigation_usuario) {
                finish();
                Intent intent = new Intent(getApplicationContext(), usuarioActivity.class);
                intent.putExtra("usuario",nombreUsuario);
                startActivity(intent);
                return true;
            }


            return true;
        });

        FloatingActionButton fButton = findViewById(R.id.fab);
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), habitosActivity.class);
                intent.putExtra("usuario",nombreUsuario);
                startActivity(intent);

            }
        });



    }
}