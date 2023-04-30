package com.juanvladimir13.arquitectura.producto;

public class DTOProducto {
    public final String id;
    public final String nombre;
    public final String marca;

    public DTOProducto(String id, String nombre, String marca) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "id: " + id + " nom: " + nombre;
    }
}
