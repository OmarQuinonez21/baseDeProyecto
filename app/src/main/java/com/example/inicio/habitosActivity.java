package com.example.inicio;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class habitosActivity extends AppCompatActivity  {

    EditText nombre, categoria, nDias;

    Button saveHabit;

    CheckBox cbLunes, cbMartes, cbMiercoles, cbJueves, cbViernes, cbSabado, cbDomingo;
    String name, category, diasnumero, lunes, martes, miercoles, jueves, viernes, sabado, domingo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitos);
        getSupportActionBar().hide(); //esconde el titulo de la app para usar toda la pantalla

        saveHabit=(Button) findViewById(R.id.btn_habitoGuardar);
        nombre=findViewById(R.id.et_nombreHabito);
        categoria=findViewById(R.id.et_categoriaHabito);
        nDias=findViewById(R.id.et_Frecuencia);
        cbLunes=(CheckBox) findViewById(R.id.checkbox_lunes);
        cbMartes=(CheckBox) findViewById(R.id.checkbox_martes);
        cbMiercoles=(CheckBox) findViewById(R.id.checkbox_miercoles);
        cbJueves=(CheckBox) findViewById(R.id.checkbox_jueves);
        cbViernes=(CheckBox) findViewById(R.id.checkbox_viernes);
        cbSabado=(CheckBox) findViewById(R.id.checkbox_sabado);
        cbDomingo=(CheckBox) findViewById(R.id.checkbox_domingo);


        saveHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCheckboxClicked(view);
                name=nombre.getText().toString().trim();
                category=categoria.getText().toString().trim();
                diasnumero=nDias.getText().toString().trim();
                guardarHabito(name,category,diasnumero,lunes,martes,miercoles,jueves,viernes,sabado,domingo);
                nombre.setText("");
                categoria.setText("");
                nDias.setText("");
                cbLunes.setChecked(false);
                cbMartes.setChecked(false);
                cbMiercoles.setChecked(false);
                cbJueves.setChecked(false);
                cbViernes.setChecked(false);
                cbSabado.setChecked(false);
                cbDomingo.setChecked(false);

            }
        });

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
    public void guardarHabito(String nombre, String categoria, String numDias, String m, String ma, String mier,String juev, String vier, String sab, String dom){
        bdHelper databaseBDhelper = new bdHelper(this);
        databaseBDhelper.guardarHabito(name,category,diasnumero,lunes,martes,miercoles,jueves,viernes,sabado,domingo);
        showDialog("Registro", "Se ha registrado correctamente");
    }
    private void showDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?


        if(cbLunes.isChecked()){
        lunes = "1";

        }else{
            lunes = "0";
        }
        if(cbMartes.isChecked()){
        martes="1";
        }else{
            martes="0";
        }
        if(cbMiercoles.isChecked()){
        miercoles="1";
        }else{
            miercoles="0";
        }
        if(cbJueves.isChecked()){
        jueves="1";
        }else{
            jueves="0";
        }
        if(cbViernes.isChecked()){
        viernes="1";
        }else{

        }
        if(cbSabado.isChecked()){
        sabado="1";
        }else{
            sabado="0";
        }
        if(cbDomingo.isChecked()){
        domingo="1";
        }else{
            domingo="0";
        }


    }
}