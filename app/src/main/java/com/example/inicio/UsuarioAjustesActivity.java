package com.example.inicio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class UsuarioAjustesActivity extends AppCompatActivity {
    private static final int REQUEST_SELECT_IMAGE = 100;
    private static final int REQUEST_CODE_PERMISSION = 123;
    int comprobador_cambioImagen=0;
    String nombreUsuario,id,img;
    Uri imagen,selectedImageUri;
    ImageView imgUser, btnAtras;
    EditText et_name,et_user,et_pass;
    Button btn_actualizar, btn_cambiarImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_ajustes);
        getSupportActionBar().hide();
        et_name = (EditText) findViewById(R.id.etxt_name);
        et_user = (EditText) findViewById(R.id.etxt_user);
        et_pass = (EditText) findViewById(R.id.etxt_pass);
        btn_actualizar = (Button) findViewById(R.id.btn_guardarCambios);
        btn_cambiarImg = (Button) findViewById(R.id.btn_changeImagen);
        imgUser = (ImageView) findViewById(R.id.imgU_user);
        btnAtras = (ImageView) findViewById(R.id.imgV_btnAtras);
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
        btn_cambiarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_SELECT_IMAGE);
                comprobador_cambioImagen=1;
            }
        });
        btn_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCambios(nombreUsuario);
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
    public void guardarCambios(String user){
        bdHelper dbHelper = new bdHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String imagen;
        if (comprobador_cambioImagen==0){
            imagen=img;
        }
        else {
            imagen=selectedImageUri.toString();
        }
        String sql = "UPDATE usuarios SET nombre='" + et_name.getText().toString().trim() + "', "+
                "username= '" + et_user.getText().toString().trim() + "', " +
                "password= '" + et_pass.getText().toString().trim() + "', " +
                "img= '" + imagen + "' WHERE id= '" + id + "'";
        db.execSQL(sql);
        db.close();
        showDialog("Actualización", "Se han actualizado los datos correctamente");
        nombreUsuario = et_user.getText().toString().trim();
    }
    public void asignarDatos(String user) {
        bdHelper dbHelper = new bdHelper(this);
        Cursor cursor = dbHelper.obtenerDatosUsuario(user);
        if (cursor != null && cursor.moveToFirst()) {
            id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
            et_name.setText(nombre);
            String username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
            et_user.setText(username);
            String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            et_pass.setText(password);
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