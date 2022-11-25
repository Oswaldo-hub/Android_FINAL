package com.example.evidenciafinalv5;



import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public  class EditarInventario extends AppCompatActivity {
    private EditText et_numero, et_nombre ,et_existencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_editar_inventario);

        et_numero = (EditText)findViewById(R.id.txt_numero);
        et_nombre = (EditText) findViewById(R.id.txt_nombre);
        et_existencia = (EditText)findViewById(R.id.txt_existencia);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null,1);

    }

    public void Buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String numero = et_numero.getText().toString();

        if (!numero.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery("select nombre, existencia from articulos where numero =" + numero, null);
            if (fila.moveToFirst()) {
                et_nombre.setText(fila.getString(0));
                et_existencia.setText(fila.getString(1));
                BaseDeDatos.close();
            }else {
                Toast.makeText(this,"No existe el producto",Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        } else {
            Toast.makeText(this,"Debes introducir el numero del producto para eliminar",Toast.LENGTH_SHORT).show();
        }
    }
    public void Agregar (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String numero = et_numero.getText().toString();
        String nombre = et_nombre.getText().toString();
        String existencia = et_existencia.getText().toString();

        if (!numero.isEmpty() && !nombre.isEmpty() && !existencia.isEmpty()) {
            ContentValues registro = new ContentValues();

            registro.put("numero", numero);
            registro.put("nombre", nombre);
            registro.put("existencia", existencia);

            BaseDeDatos.insert("articulos", null, registro);
            BaseDeDatos.close();

            et_numero.setText("");
            et_nombre.setText("");
            et_existencia.setText("");

            Toast.makeText(this, "Registro de producto exitoso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"Debes llenar todos los campos",Toast.LENGTH_SHORT).show();
        }
    }

    public void Editar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String numero = et_numero.getText().toString();
        String nombre = et_nombre.getText().toString();
        String existencia = et_existencia.getText().toString();

        if (!numero.isEmpty() && !nombre.isEmpty() && !existencia.isEmpty()){

            ContentValues registro = new ContentValues();
            registro.put("numero", numero);
            registro.put("nombre", nombre);
            registro.put("existencia", existencia);

            int cantidad = BaseDeDatos.update("articulos", registro,"numero=" + numero, null);
            BaseDeDatos.close();

            if (cantidad == 1){
                Toast.makeText(this,"El producto fue editado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"El producto no existe", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,"Debes llenar todos los campos", Toast.LENGTH_SHORT).show();

        }

    }
    public void Eliminar (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String numero = et_numero.getText().toString();

        if (!numero.isEmpty()) {

            int cantidad = BaseDeDatos.delete("articulos", "numero=" + numero, null);
            BaseDeDatos.close();

            et_numero.setText("");
            et_nombre.setText("");
            et_existencia.setText("");

            if (cantidad == 1) {
                Toast.makeText(this, "Producto eliminado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Producto no existente", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,"Debes introducir el numero del articulo",Toast.LENGTH_SHORT).show();
        }

    }
}