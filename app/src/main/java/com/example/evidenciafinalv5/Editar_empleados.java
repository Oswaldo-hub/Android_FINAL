package com.example.evidenciafinalv5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Editar_empleados extends AppCompatActivity {
    private EditText et_numero, et_nombre ,et_existencia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_empleados);
        et_numero = (EditText)findViewById(R.id.txt_id);
        et_nombre = (EditText) findViewById(R.id.txt_nombre2);
        et_existencia = (EditText)findViewById(R.id.txt_salario);
        Empleado_db admine = new Empleado_db(this, "administracione", null,1);

    }

    public void Buscar_emp(View view){
        Empleado_db empleado_db = new Empleado_db(this, "administracione", null,1);
        SQLiteDatabase BaseDatos = empleado_db.getWritableDatabase();

        String numero = et_numero.getText().toString();

        if (!numero.isEmpty()){
            Cursor fila = BaseDatos.rawQuery("select nombre, salario from personal where numero =" + numero, null);
            if (fila.moveToFirst()) {
                et_nombre.setText(fila.getString(0));
                et_existencia.setText(fila.getString(1));
                BaseDatos.close();
            }else {
                Toast.makeText(this,"No existe el empleado",Toast.LENGTH_SHORT).show();
                BaseDatos.close();
            }
        } else {
            Toast.makeText(this,"Debes introducir el ID del empleado",Toast.LENGTH_SHORT).show();
        }
    }
    public void Agregar_emp (View view){
        Empleado_db empleado_db = new Empleado_db(this, "administracione", null,1);
        SQLiteDatabase BaseDatos = empleado_db.getWritableDatabase();

        String numero = et_numero.getText().toString();
        String nombre = et_nombre.getText().toString();
        String salario = et_existencia.getText().toString();

        if (!numero.isEmpty() && !nombre.isEmpty() && !salario.isEmpty()) {
            ContentValues registro = new ContentValues();

            registro.put("numero", numero);
            registro.put("nombre", nombre);
            registro.put("salario", salario);

            BaseDatos.insert("personal", null, registro);
            BaseDatos.close();

            et_numero.setText("");
            et_nombre.setText("");
            et_existencia.setText("");

            Toast.makeText(this, "Registro del empleado exitoso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"Debes llenar todos los campos",Toast.LENGTH_SHORT).show();
        }
    }

    public void Editar_emp(View view){
        Empleado_db empleado_db = new Empleado_db(this, "administracione", null,1);
        SQLiteDatabase BaseDatos = empleado_db .getWritableDatabase();

        String numero = et_numero.getText().toString();
        String nombre = et_nombre.getText().toString();
        String salario = et_existencia.getText().toString();

        if (!numero.isEmpty() && !nombre.isEmpty() && !salario.isEmpty()){

            ContentValues registro = new ContentValues();
            registro.put("numero", numero);
            registro.put("nombre", nombre);
            registro.put("salario", salario);

            int cantidad = BaseDatos.update("personal", registro,"numero=" + numero, null);
            BaseDatos.close();

            if (cantidad == 1){
                Toast.makeText(this,"El empleado fue editado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"El empleado no existe", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,"Debes llenar todos los campos", Toast.LENGTH_SHORT).show();

        }

    }
    public void Eliminar_emp (View view){
        Empleado_db empleado_db = new Empleado_db(this, "administracione", null,1);
        SQLiteDatabase BaseDatos= empleado_db .getWritableDatabase();

        String numero = et_numero.getText().toString();

        if (!numero.isEmpty()) {

            int cantidad = BaseDatos.delete("personal", "numero=" + numero, null);
            BaseDatos.close();

            et_numero.setText("");
            et_nombre.setText("");
            et_existencia.setText("");

            if (cantidad == 1) {
                Toast.makeText(this, "Empleado eliminado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Empleado no existente", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,"Debes introducir el numero del empleado",Toast.LENGTH_SHORT).show();
        }

    }}
