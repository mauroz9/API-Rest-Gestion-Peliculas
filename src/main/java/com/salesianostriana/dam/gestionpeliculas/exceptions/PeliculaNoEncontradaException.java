package com.salesianostriana.dam.gestionpeliculas.exceptions;

public class PeliculaNoEncontradaException extends EntidadNoEncontradaException {
    public PeliculaNoEncontradaException(String message) {
        super(message);
    }

    public PeliculaNoEncontradaException(Long id){
        super("No se ha encontrado la película con ID: " + id);
    }
}
