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
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class usuarioActivity extends AppCompatActivity {
    String nombreUsuario;
    Uri imagen;
    private static final int REQUEST_CODE_PERMISSION = 123;
    TextView txt_ajustes, txt_trofeos, txt_logOut, usuario;
    ImageView imgV_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nombreUsuario = getIntent().getStringExtra("usuario");

        setContentView(R.layout.activity_usuario);
        getSupportActionBar().hide(); //esconde el titulo de la app para usar toda la pantalla

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
        imgV_usuario = (ImageView) findViewById(R.id.imgU_user);
        txt_ajustes = (TextView) findViewById(R.id.txtV_ajustes);
        txt_trofeos = (TextView) findViewById(R.id.txtV_trofeos);
        txt_logOut = (TextView) findViewById(R.id.txtV_logOut);
        usuario = (TextView) findViewById(R.id.txtV_user);
        txt_ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para la acción cuando se hace clic en el TextView
                Intent intent = new Intent(getApplicationContext(), UsuarioAjustesActivity.class);
                startActivity(intent);
            }
        });
        asignarDatos(nombreUsuario);
        Log.d("Usuario", nombreUsuario);
        BottomNavigationView navPomo = findViewById(R.id.bottomNavigationView);
        navPomo.setSelectedItemId(R.id.navigation_usuario);
        navPomo.setBackground(null);

        navPomo.setOnItemSelectedListener(item -> {
            if (item.getItemId()==R.id.navigation_rut) {
                finish();
                Intent intent = new Intent(getApplicationContext(), RutinaActivity.class);
                intent.putExtra("usuario",nombreUsuario);
                startActivity(intent);
                return true;
            }else if (item.getItemId()==R.id.navigation_home) {
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("usuario",nombreUsuario);
                startActivity(intent);
                return true;
            } else if (item.getItemId()==R.id.navigation_pomo){
                finish();
                Intent intent = new Intent(getApplicationContext(), pomodoroActivity.class);
                intent.putExtra("usuario",nombreUsuario);
                startActivity(intent);
                return true;
            }


            return true;
        });
    }

    public void asignarDatos(String user) {
        bdHelper dbHelper = new bdHelper(this);
        Cursor cursor = dbHelper.obtenerDatosUsuario(user);
        if (cursor != null && cursor.moveToFirst()) {
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
            usuario.setText(nombre);
            String img = cursor.getString(cursor.getColumnIndexOrThrow("img"));
            imagen = Uri.parse(img);
            // Verificar si el permiso READ_EXTERNAL_STORAGE está concedido
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Solicitar el permiso en tiempo de ejecución si no está concedido
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
            } else {
                // Si el permiso está concedido, cargar la imagen desde la dirección guardada en la base de datos
                imgV_usuario.setImageURI(imagen);
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
                imgV_usuario.setImageURI(imagen);
            } else {
                // Si el permiso fue denegado, manejar la situación en consecuencia (mostrar un mensaje, deshabilitar la funcionalidad, etc.)
            }
        }
    }
}