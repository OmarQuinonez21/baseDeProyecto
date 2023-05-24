package com.example.inicio;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Calendar;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_SELECT_IMAGE = 100;
    //Variables para invocar un calendario
    ImageButton btnRegresar;
    Button btnBorrar, btnRegistrar, btnCambiar;
    EditText fecha;
    private int dia,mes,ano;
    EditText et_nom, et_user, et_correo, et_fecha, et_pais, et_ciudad, et_pass;
    ImageView imgUser;
    String nombre, usuario, correo, Sfecha, pais, ciudad, pass, img;
    Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        getSupportActionBar().hide();
        btnRegresar=(ImageButton)findViewById(R.id.regreso_reg);
        btnBorrar=(Button)findViewById(R.id.btnborrar);
        btnRegistrar=(Button)findViewById(R.id.btnregistrar);
        btnCambiar=(Button)findViewById(R.id.btn_cambiar);
        fecha = (EditText)findViewById(R.id.et_fecha);
        fecha.setOnClickListener(this);
        imgUser = (ImageView) findViewById(R.id.img_user);
        et_nom=findViewById(R.id.et_nombre);
        et_user=findViewById(R.id.et_user);
        et_correo=findViewById(R.id.et_correo);
        et_fecha= findViewById(R.id.et_fecha);
        et_pais=findViewById(R.id.et_pais);
        et_ciudad=findViewById(R.id.et_ciudad);
        et_pass=findViewById(R.id.et_pass);

        btnRegresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onBackPressed();
            }
        });
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_nom.setText("");     et_user.setText("");     et_correo.setText("");
                et_fecha.setText("");   et_pais.setText("");     et_ciudad.setText("");
                et_pass.setText("");
            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                nombre=et_nom.getText().toString().trim();
                usuario=et_user.getText().toString().trim();
                correo=et_correo.getText().toString().trim();
                Sfecha= et_fecha.getText().toString().trim();
                pais=et_pais.getText().toString().trim();
                ciudad=et_ciudad.getText().toString().trim();
                pass=et_pass.getText().toString().trim();
                img = selectedImageUri.toString();
                signUp(nombre,usuario,correo,Sfecha,pais,ciudad,pass,img);
            }
        });
        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_SELECT_IMAGE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SELECT_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                selectedImageUri = data.getData();
                imgUser.setImageURI(selectedImageUri);
            }
        }
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

    public void signUp(String nombre, String user, String correo, String sfecha, String pais, String ciudad,String pass,String img){
        bdHelper databaseBDhelper = new bdHelper(this);
        databaseBDhelper.signUp(nombre,user,correo,sfecha,pais,ciudad,pass,img);
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


}