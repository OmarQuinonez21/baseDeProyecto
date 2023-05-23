package com.example.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Calendar;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    //Variables para invocar un calendario
    ImageButton btnRegresar;
    EditText fecha;
    private int dia,mes,ano;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        getSupportActionBar().hide();
        btnRegresar=(ImageButton)findViewById(R.id.regreso_reg);
        fecha = (EditText)findViewById(R.id.fecha);
        fecha.setOnClickListener(this);

        btnRegresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onBackPressed();
            }
        });
    }

    //Al hacer click se mostrara un calendario  y se guardan los valores seleccionados en el texto
    @Override
    public void onClick(View v) {
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fecha.setText(year+"-"+(month+1)+"-"+dayOfMonth);
            }
        }
        ,dia,mes,ano);
        datePickerDialog.show();
    }

}