package com.juanvladimir13.arquitectura.producto;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.juanvladimir13.arquitectura.Template;

public class DatoProducto extends Template<DTOProducto> {
    private String id;
    private String nombre;
    private String marca;

    private SQLiteDatabase db;

    public DatoProducto(SQLiteDatabase db) {
        this.id = "";
        this.nombre = "";
        this.marca = "";
        this.db = db;
    }

    public void setAttributesData(DTOProducto dto) {
        id = dto.id;
        nombre = dto.nombre;
        marca = dto.marca;
    }

    public DTOProducto saveInDatabase() {
        ContentValues values = new ContentValues();
        values.put("marca", marca);
        values.put("nombre", nombre);

        if (!id.isEmpty()) {
            values.put("id", id);
        }

        long affectedRows = !id.isEmpty() ?
                db.update("producto", values, "id=" + id, null) :
                db.insert("producto", null, values);

        if (affectedRows == 0)
            return null;

        DTOProducto dto = !id.isEmpty() ?
                findRecordInDatabase("id", id) :
                findRecordInDatabase("nombre", nombre);

        return dto;
    }

    public DTOProducto findRecordInDatabase(String columnName, String value) {
        DTOProducto dto = null;

        String sql = String.format("select * from producto where %s='%s';", columnName, value);
        Cursor row = db.rawQuery(sql, null);
        if (row.moveToFirst()) {
            dto = new DTOProducto(
                    row.getString(0),
                    row.getString(1),
                    row.getString(2)
            );
        }
        return dto;
    }

    public boolean deleteRecordInDatabase() {
        return db.delete("producto", "id=" + id, null) > 0;
    }

    @Override
    public Cursor getCursor() {
        String sql = "SELECT * FROM producto;";
        return db.rawQuery(sql, null);
    }

    @Override
    public DTOProducto getModel(Cursor cursor) {
        return new DTOProducto(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2)
        );
    }
}
