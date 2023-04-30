package com.juanvladimir13.arquitectura.utils;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE producto ( " +
                "id INTEGER NOT NULL, " +
                "nombre varchar(50) NOT NULL UNIQUE, " +
                "marca varchar(150) NOT NULL, " +
                "PRIMARY KEY(id AUTOINCREMENT))");

        db.execSQL("CREATE TABLE categoria ( " +
                "id INTEGER NOT NULL, " +
                "nombre varchar(50) NOT NULL UNIQUE, " +
                "descripcion varchar(150) NOT NULL, " +
                "PRIMARY KEY(id AUTOINCREMENT))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + "producto");
        db.execSQL("DROP TABLE IF EXISTS " + "categoria");
        onCreate(db);
    }
}
