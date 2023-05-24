package com.example.inicio;

import static com.example.inicio.bdHelper.TABLE_HABITS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.inicio.adaptadoresRutina.ListaRutinasAdapter;
import com.example.inicio.databinding.ActivityMainBinding;
import com.example.inicio.entidadesRutina.Rutina;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RutinaActivity extends AppCompatActivity {
    String nombreUsuario;
    RecyclerView listaRutinas;
    ArrayList<Rutina> listaArrayRutinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nombreUsuario = getIntent().getStringExtra("usuario");

        setContentView(R.layout.activity_rutina);
        getSupportActionBar().hide(); //esconde el titulo de la app para usar toda la pantalla
        listaRutinas=findViewById(R.id.lista_habitos);

        listaRutinas.setLayoutManager(new LinearLayoutManager(this));
        dbRutina BaseDatosRutina =new dbRutina(RutinaActivity.this);
        listaArrayRutinas = new ArrayList<>();
        ListaRutinasAdapter adapterRutina = new ListaRutinasAdapter(BaseDatosRutina.mostrarContactos());
        listaRutinas.setAdapter(adapterRutina);

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