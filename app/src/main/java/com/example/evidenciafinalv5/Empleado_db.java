package com.example.evidenciafinalv5;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Empleado_db extends SQLiteOpenHelper {
    public Empleado_db(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
    super(context,name,factory,version);
}
    @Override
    public void onCreate(SQLiteDatabase BaseDatos){

        BaseDatos.execSQL("create  table personal(numero int primary key, nombre text,salario int)");


    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){

    }
}