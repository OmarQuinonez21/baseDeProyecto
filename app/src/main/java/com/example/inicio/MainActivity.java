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

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
        navView.setSelectedItemId(R.id.navigation_home);
        navView.setBackground(null);

        binding.bottomNavigationView.setOnItemSelectedListener(item->{
            if(item.getItemId()==R.id.navigation_pomo){
                startActivity(new Intent(getApplicationContext(), pomodoroActivity.class));
                return true;
            } else if (item.getItemId()==R.id.navigation_rut) {
                startActivity(new Intent(getApplicationContext(), RutinaActivity.class));

                return true;
            }
            else if (item.getItemId()==R.id.navigation_usuario) {
                startActivity(new Intent(getApplicationContext(), usuarioActivity.class));

                return true;
            }


            return true;
        });

        FloatingActionButton fButton = findViewById(R.id.fab);
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), habitosActivity.class));

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