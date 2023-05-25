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
    //Esta variable puede ser cualquier numero
    private static final int REQUEST_CODE_PERMISSION = 123;
    //Variable para guardar la dirección de imagen desde la base de datos
    String img;
    //Variable para mostrar la imagen con un setImageUri en un ImageView
    Uri imagen;
    //Variables para recolectar los ImageView del activity_trofeos
    ImageView imgUser, btnAtras;
    //Variables para recolectar los TextView del activity_trofeos
    TextView txtV_nombre,txtv_user;
    //Variable para recolectar el usuario que se recibe desde otra activity con un intent
    String nombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_trofeos);
        getSupportActionBar().hide();
        // Hacemos conexión java-xml
        btnAtras = (ImageView) findViewById(R.id.imgV_btnAtras);
        txtV_nombre = (TextView) findViewById(R.id.txtV_user);
        txtv_user = (TextView) findViewById(R.id.txtv_userMini);
        imgUser = (ImageView) findViewById(R.id.imgU_user);
        // Obtenemos el usuario de otra activity
        nombreUsuario = getIntent().getStringExtra("usuario");
        // Llamamos el método asignarDatos y le mandamos como parametro nombreUsuario
        asignarDatos(nombreUsuario);
        // Si el usuario hace click a la flecha de atras, se íra para atras
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
    // Método para asignar los datos del usuario (Nombre, usuario e imagen) y que se vean
    // en el activity.
    public void asignarDatos(String user) {
        // Creamos un objeto bdHelper, para poder acceder a todos los métodos de la clase bdHelper.java
        bdHelper dbHelper = new bdHelper(this);
        // Creamos un objeto cursor, que nos ayuda a acceder y manipular los resultados obtenidos de
        // una consulta que se realiza a la base de datos SQLite
        Cursor cursor = dbHelper.obtenerDatosUsuario(user);
        // Checamos si el cursor tiene datos
        if (cursor != null && cursor.moveToFirst()) {
            // Obtenemos el valor de la columna "nombre" del registro obtenido y lo guardamos
            // en la variable String nombre
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
            // Mostramos el nombre del usuario
            txtV_nombre.setText(nombre);
            // Obtenemos el valor de la columna "username" del registro obtenido y lo guardamos
            // en la variable String nombre
            String username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
            // Mostramos el usuario
            txtv_user.setText(username);
            // Obtenemos la string de la dirección de imagen
            img = cursor.getString(cursor.getColumnIndexOrThrow("img"));
            // La convertimos en formato Uri y la guardamos en Uri imagen
            imagen = Uri.parse(img);
            // Verificar si el permiso READ_EXTERNAL_STORAGE está concedido
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Solicitar el permiso en tiempo de ejecución si no está concedido
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
            } else {
                // Si el permiso está concedido, cargar la imagen desde la dirección guardada en la base de datos
                // Le damos formato circular a la imagen
                imgUser.setBackgroundResource(R.drawable.circle_background);
                imgUser.setImageURI(imagen);
            }
        }
    }
    // Método para, aunque suene redundante, poner la imagen en caso de que el usuario acepte la solicitud
    // al acceso a su memoria
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
            }
        }
    }
}