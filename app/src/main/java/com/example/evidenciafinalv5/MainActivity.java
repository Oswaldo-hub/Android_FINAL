package com.example.evidenciafinalv5;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.imageView2);
         ImageView imageView2 = findViewById(R.id.imageView);
         ImageView imageView3 = findViewById(R.id.imagen_empleados);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setTitle("           Snack Video las puentes");
         imageView3.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent  = new Intent(getApplicationContext(), Editar_empleados.class);
                 ;
                 startActivity(intent);;
             }

         });
         imageView2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent  = new Intent(getApplicationContext(), Ver_registros.class);
                 ;
                 startActivity(intent);;
             }
         });
         imageView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent  = new Intent(getApplicationContext(),EditarInventario.class);
                 ;
                 startActivity(intent);
             }
         });

    }
}