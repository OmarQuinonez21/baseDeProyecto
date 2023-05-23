package com.example.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class IniciarSesionActivity extends AppCompatActivity {

    Button btnLogin,btnRegister;
    EditText user;
    EditText pass;
    String usuario;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        getSupportActionBar().hide();
        btnLogin=findViewById(R.id.log);
        btnRegister=findViewById(R.id.reg);
        user=findViewById(R.id.user);
        pass=findViewById(R.id.pass);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                usuario=user.getText().toString().trim();
                password=pass.getText().toString().trim();
                if (signIn(usuario,password)) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }

            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean signIn(String user, String pass){
        bdHelper databaseBDhelper = new bdHelper(this);
        boolean login = databaseBDhelper.signIn(user, pass);
        return login;
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