package com.example.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.inicio.entidadesRutina.Rutina;

import java.util.ArrayList;

public class VerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText nombre, nDias;
    Spinner sCategorias;
    String nombreRutina;
    ImageView btnAtrasVer;

    Rutina rutina;
    int id = 0;

    String nombreUsuario;

    ArrayList<String> lista = new ArrayList<>();

    Button saveHabit;

    CheckBox cbLunes, cbMartes, cbMiercoles, cbJueves, cbViernes, cbSabado, cbDomingo;
    String name, category, diasnumero, lunes, martes, miercoles, jueves, viernes, sabado, domingo, nameEditando;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
        getSupportActionBar().hide(); //esconde el titulo de la app para usar toda la pantalla
        btnAtrasVer = (ImageView) findViewById(R.id.imgV_btnAtrasVer);
        //Aqui yahel puede agregar intent para volver de la vista editar
        nombreUsuario = getIntent().getStringExtra("usuario");
        saveHabit=(Button) findViewById(R.id.btn_habitoEditar);
        nombre=findViewById(R.id.txtHabito);
        sCategorias = findViewById(R.id.txtCategoria);
        agregandoValores();
        darClic();

        nDias=findViewById(R.id.txtFrecuencia);
        cbLunes=(CheckBox) findViewById(R.id.txtLunes);
        cbMartes=(CheckBox) findViewById(R.id.txtMartes);
        cbMiercoles=(CheckBox) findViewById(R.id.txtMiercoles);
        cbJueves=(CheckBox) findViewById(R.id.txtJueves);
        cbViernes=(CheckBox) findViewById(R.id.txtViernes);
        cbSabado=(CheckBox) findViewById(R.id.txtSabado);
        cbDomingo=(CheckBox) findViewById(R.id.txtDomingo);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("IdHabitos");
            }
        }else{
            id =(int) savedInstanceState.getSerializable("IdHabitos");
        }
        dbRutina dbContactos = new dbRutina(VerActivity.this);
        rutina = dbContactos.verContacto(id);

        if(rutina != null){
            nombre.setText(rutina.getHabito());
            nameEditando=rutina.getHabito();

        }
        saveHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCheckboxClicked(view);
                if(nombre.getText().toString().length()>18){
                    showDialog("Error", "Use 18 caracteres como maximo");
                } else if (Integer.parseInt(nDias.getText().toString())<1) {
                    showDialog("Error", "Ingrese valor mayor a 0 en frecuencia");
                } else{
                    name=nombre.getText().toString().trim();

                    //aqui va categoria


                    diasnumero=nDias.getText().toString().trim();
                    editarHabito();

                    nombre.setText("");
                    //categoria.setText("");
                    nDias.setText("");
                    cbLunes.setChecked(false);
                    cbMartes.setChecked(false);
                    cbMiercoles.setChecked(false);
                    cbJueves.setChecked(false);
                    cbViernes.setChecked(false);
                    cbSabado.setChecked(false);
                    cbDomingo.setChecked(false);
                }

            }
        });

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
            viernes="0";
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



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        adapterView.getSelectedItem();
        if(adapterView.getId()==R.id.txtCategoria){
            Toast.makeText(this,adapterView.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            category = sCategorias.getSelectedItem().toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        category = "Otro";
    }
    private void agregandoValores()
    {
        lista.add("Salud");
        lista.add("Deporte");
        lista.add("Estudio");
        lista.add("Laboral");
        lista.add("Economico");
        lista.add("Religion");
        lista.add("Espiritual");
        lista.add("Social");
        lista.add("Recreativo");
        lista.add("Otro");

    }
    private void darClic()
    {
        sCategorias.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_categorias, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCategorias.setAdapter(adapter);
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
    public void editarHabito(){
        bdHelper dbHelper = new bdHelper(this);
        // Creamos un objeto SQLiteDatabase que lo que hace ayudarnos a obtener una base de datos que
        // hicimos en bdHelper
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Ejecutamos la consulta UPDATE
        String sql = "UPDATE habitos SET habito='" + name + "', "+
                "categoria= '" + category + "', " +
                "frecuencia= '" + diasnumero + "', " +
                "lunes= '" + lunes + "', " +
                "martes= '" + martes + "', " +
                "miercoles= '" + miercoles + "', " +
                "jueves= '" + jueves + "', " +
                "viernes= '" + viernes + "', " +
                "sabado= '" + sabado + "', " +
                "domingo= '" + domingo +"' WHERE habito= '" + nameEditando + "'";
        db.execSQL(sql);
        db.close();
        showDialog("Actualización", "Se han actualizado los datos correctamente");
        finish();
        Intent intentEdit = new Intent(getApplicationContext(), RutinaActivity.class);
        intentEdit.putExtra("usuario",nombreUsuario);
        startActivity(intentEdit);
    }
}