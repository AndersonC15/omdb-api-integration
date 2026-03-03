package com.AndersonC15.screenmatch.model;

public enum Categoria {
    ACCION("Action"),
    ROMANCE("Romance"),
    DRAMA("Drama"),
    COMEDIA("Comedy"),
    CRIMEN("Crime");

    private String categoriaOmdb;
    Categoria(String categoriaOmdb) {
        this.categoriaOmdb = categoriaOmdb;
    }
}
