package com.juanvladimir13.arquitectura.producto;

import java.util.List;

public class NegocioProducto {
    private DatoProducto dato;

    public NegocioProducto(DatoProducto dato) {
        this.dato = dato;
    }

    public void setAttributesData(DTOProducto dto) {
        dato.setAttributesData(dto);
    }

    public DTOProducto saveRecord() {
        return dato.saveInDatabase();
    }

    public List<DTOProducto> rowsList() {
        return dato.rowsList();
    }

    public boolean deleteRecord() {
        return dato.deleteRecordInDatabase();
    }

    public DTOProducto findRecord(String id) {
        return dato.findRecordInDatabase("id", id);
    }
}
