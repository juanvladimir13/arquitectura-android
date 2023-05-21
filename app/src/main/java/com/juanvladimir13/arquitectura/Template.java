package com.juanvladimir13.arquitectura;

import android.database.Cursor;

import java.util.LinkedList;
import java.util.List;

public abstract class Template<T>{
    public List<T> rowsList() {
        List<T> rows = new LinkedList<>();
        Cursor cursor =  getCursor();

        while (cursor.moveToNext()) {
            T dto = getModel(cursor);
            rows.add(dto);
        }
        return rows;
    }

    public abstract Cursor getCursor();
    public abstract T getModel(Cursor cursor);
}
