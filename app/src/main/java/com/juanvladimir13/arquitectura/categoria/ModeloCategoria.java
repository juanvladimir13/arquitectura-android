package com.juanvladimir13.arquitectura.categoria;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

public class ModeloCategoria {
    private String id;
    private String nombre;
    private String descripcion;

    private SQLiteDatabase db;

    public ModeloCategoria(SQLiteDatabase db) {
        this.id = "";
        this.nombre = "";
        this.descripcion = "";
        this.db = db;
    }

    public void setAttributesData(DTOCategoria dto) {
        id = dto.id;
        nombre = dto.nombre;
        descripcion = dto.descripcion;
    }

    public DTOCategoria saveInDatabase() {
        ContentValues values = new ContentValues();
        values.put("descripcion", descripcion);
        values.put("nombre", nombre);

        if (!id.isEmpty()) {
            values.put("id", id);
        }

        long affectedRows = !id.isEmpty() ?
                db.update("categoria", values, "id=" + id, null) :
                db.insert("categoria", null, values);

        if (affectedRows == 0)
            return null;

        DTOCategoria dto = !id.isEmpty() ?
                findRecordInDatabase("id", id) :
                findRecordInDatabase("nombre", nombre);

        return dto;
    }

    public DTOCategoria findRecordInDatabase(String columnName, String value) {
        DTOCategoria dto = null;

        String sql = String.format("select * from categoria where %s='%s';", columnName, value);
        Cursor row = db.rawQuery(sql, null);
        if (row.moveToFirst()) {
            dto = new DTOCategoria(
                    row.getString(0),
                    row.getString(1),
                    row.getString(2)
            );
        }
        return dto;
    }

    public List<DTOCategoria> rowsList() {
        List<DTOCategoria> rows = new LinkedList<>();

        String sql = String.format("select * from categoria");
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            DTOCategoria dto = new DTOCategoria(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );
            rows.add(dto);
        }
        return rows;
    }

    public boolean deleteRecordInDatabase() {
        return db.delete("categoria", "id=" + id, null) > 0;
    }
}
