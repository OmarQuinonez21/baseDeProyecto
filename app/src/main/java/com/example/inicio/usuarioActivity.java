package com.example.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.inicio.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class usuarioActivity extends AppCompatActivity {

    ImageButton btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_usuario);
        getSupportActionBar().hide(); //esconde el titulo de la app para usar toda la pantalla

        FloatingActionButton fButton = findViewById(R.id.fab);
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), habitosActivity.class));

            }
        });

        btnRegresar = (ImageButton) findViewById(R.id.regreso_user);


        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        BottomNavigationView navPomo = findViewById(R.id.bottomNavigationView);
        navPomo.setSelectedItemId(R.id.navigation_usuario);
        navPomo.setBackground(null);

        navPomo.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_rut) {
                finish();
                startActivity(new Intent(getApplicationContext(), RutinaActivity.class));
                return true;
            } else if (item.getItemId() == R.id.navigation_home) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                return true;
            } else if (item.getItemId() == R.id.navigation_pomo) {
                finish();
                startActivity(new Intent(getApplicationContext(), pomodoroActivity.class));

                return true;
            }


            return true;
        });
    }
}