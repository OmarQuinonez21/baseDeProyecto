package com.example.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class habitosActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitos);
        getSupportActionBar().hide(); //esconde el titulo de la app para usar toda la pantalla

        BottomNavigationView navHabitos = findViewById(R.id.bottomNavigationView);
        navHabitos.setBackground(null);

        navHabitos.setOnItemSelectedListener(item->{
            if(item.getItemId()==R.id.navigation_pomo){
                finish();
                startActivity(new Intent(getApplicationContext(), pomodoroActivity.class));
                return true;
            } else if (item.getItemId()==R.id.navigation_home) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                return true;
            }

            else if (item.getItemId()==R.id.navigation_usuario) {
                finish();
                startActivity(new Intent(getApplicationContext(), usuarioActivity.class));

                return true;
            }
            else if (item.getItemId()==R.id.navigation_rut) {
                finish();
                startActivity(new Intent(getApplicationContext(), RutinaActivity.class));

                return true;
            }


            return true;
        });



    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        if(view.getId()==R.id.checkbox_lunes){

        }
        else if(view.getId()==R.id.checkbox_martes){

        }
        else if(view.getId()==R.id.checkbox_miercoles){

        }
        else if(view.getId()==R.id.checkbox_jueves){

        }
        else if(view.getId()==R.id.checkbox_viernes){

        }else if(view.getId()==R.id.checkbox_sabado){

        }else if(view.getId()==R.id.checkbox_domingo){

        }


    }
}