package com.juanvladimir13.arquitectura.categoria;

public class DTOCategoria {
    public final String id;
    public final String descripcion;
    public final String nombre;

    public DTOCategoria(String id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "id: " + id + " nom: " + nombre;
    }
}
