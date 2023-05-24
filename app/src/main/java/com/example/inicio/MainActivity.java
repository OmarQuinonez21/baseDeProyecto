package com.example.inicio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inicio.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

// git branch JP
public class MainActivity extends AppCompatActivity {
    String nombreUsuario;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        nombreUsuario = getIntent().getStringExtra("usuario");
        getSupportActionBar().hide();

        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
        navView.setSelectedItemId(R.id.navigation_home);
        navView.setBackground(null);

        navView.setOnItemSelectedListener(item->{

            if(item.getItemId()==R.id.navigation_pomo){
                finish();
                Intent intent = new Intent(getApplicationContext(), pomodoroActivity.class);
                intent.putExtra("usuario",nombreUsuario);
                startActivity(intent);
                return true;
            } else if (item.getItemId()==R.id.navigation_rut) {
                finish();
                Intent intent = new Intent(getApplicationContext(), RutinaActivity.class);
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

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        //Esta es la configuracion de la barra por defecto de android studio

       /* AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_hab, R.id.navigation_pomo, R.id.navigation_rut, R.id.navigation_usuario)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);*/
    }

}