package com.salesianostriana.dam.gestionpeliculas.exceptions;

public class PeliculaYaExisteException extends RuntimeException {
    public PeliculaYaExisteException(String message) {
        super(message);
    }
}
