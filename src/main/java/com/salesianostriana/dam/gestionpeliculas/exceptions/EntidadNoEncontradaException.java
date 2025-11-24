package com.salesianostriana.dam.gestionpeliculas.exceptions;

public class EntidadNoEncontradaException extends RuntimeException {
    public EntidadNoEncontradaException(String message) {
        super(message);
    }

    public EntidadNoEncontradaException(Class<?> clase, Long id){
        super("No se ha encontrado la entidad " + clase.getSimpleName() + " con ID: " + id);
    }
}
