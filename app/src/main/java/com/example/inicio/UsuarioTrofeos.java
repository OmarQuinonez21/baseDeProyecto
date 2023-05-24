package com.example.inicio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class UsuarioTrofeos extends AppCompatActivity {
    private static final int REQUEST_CODE_PERMISSION = 123;
    String img;
    Uri imagen;
    ImageView imgUser, btnAtras;
    TextView txtV_nombre,txtv_user;
    String nombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_trofeos);
        getSupportActionBar().hide();
        btnAtras = (ImageView) findViewById(R.id.imgV_btnAtras);
        txtV_nombre = (TextView) findViewById(R.id.txtV_user);
        txtv_user = (TextView) findViewById(R.id.txtv_userMini);
        imgUser = (ImageView) findViewById(R.id.imgU_user);
        nombreUsuario = getIntent().getStringExtra("usuario");
        asignarDatos(nombreUsuario);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para la acción cuando se hace clic en el TextView
                Intent intent = new Intent(getApplicationContext(), usuarioActivity.class);
                intent.putExtra("usuario", nombreUsuario);
                startActivity(intent);
            }
        });
    }
    public void asignarDatos(String user) {
        bdHelper dbHelper = new bdHelper(this);
        Cursor cursor = dbHelper.obtenerDatosUsuario(user);
        if (cursor != null && cursor.moveToFirst()) {
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
            txtV_nombre.setText(nombre);
            String username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
            txtv_user.setText(username);
            img = cursor.getString(cursor.getColumnIndexOrThrow("img"));
            imagen = Uri.parse(img);
            // Verificar si el permiso READ_EXTERNAL_STORAGE está concedido
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Solicitar el permiso en tiempo de ejecución si no está concedido
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
            } else {
                // Si el permiso está concedido, cargar la imagen desde la dirección guardada en la base de datos
                imgUser.setBackgroundResource(R.drawable.circle_background);
                imgUser.setImageURI(imagen);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            // Verificar si el permiso fue concedido
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Si el permiso fue concedido, cargar la imagen desde la dirección guardada en la base de datos
                imgUser.setBackgroundResource(R.drawable.circle_background);
                imgUser.setImageURI(imagen);
            } else {
                // Si el permiso fue denegado, manejar la situación en consecuencia (mostrar un mensaje, deshabilitar la funcionalidad, etc.)
            }
        }
    }
}