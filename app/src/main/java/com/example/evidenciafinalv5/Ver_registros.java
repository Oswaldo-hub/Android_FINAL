package com.example.evidenciafinalv5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Ver_registros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_registros);
        ImageView imageView = findViewById(R.id.ver_papeleria);
        ImageView imageView2 = findViewById(R.id.verpersonal);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null,1);
        Empleado_db empleado_db = new Empleado_db(this, "administracione", null,1);


        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase BaseDatos = empleado_db .getWritableDatabase();

                Cursor fila = BaseDatos.rawQuery("select  * from personal ", null);

                StringBuffer buffer =  new StringBuffer();
                while (fila.moveToNext()) {
                    buffer.append("ID del empleado: " + fila.getString(0)+"\n");
                    buffer.append("Nombre del empleado: " + fila.getString(1)+"\n");
                    buffer.append("Salario del empleado: " + fila.getString(2)+"\n");

                    buffer.append("------------------------------------------------------" +"\n");
                    AlertDialog.Builder builder = new AlertDialog.Builder(Ver_registros.this);
                    builder.setCancelable(true);
                    builder.setTitle("Registros de Empleados");
                    builder.setMessage(buffer.toString() );
                    builder.show();
                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

                Cursor fila = BaseDeDatos.rawQuery("select  * from articulos ", null);

                StringBuffer buffer =  new StringBuffer();
                while (fila.moveToNext()) {
                    buffer.append("Numero del articulo: " + fila.getString(0)+"\n");
                    buffer.append("Nombre del articulo: " + fila.getString(1)+"\n");
                    buffer.append("Existencia del articulo: " + fila.getString(2)+"\n");
                    buffer.append("------------------------------------------------------" +"\n");
                    AlertDialog.Builder builder = new AlertDialog.Builder(Ver_registros.this);
                    builder.setCancelable(true);
                    builder.setTitle("Registros del inventario");
                    builder.setMessage(buffer.toString() );
                    builder.show();
                }
            }
        });
    }
}