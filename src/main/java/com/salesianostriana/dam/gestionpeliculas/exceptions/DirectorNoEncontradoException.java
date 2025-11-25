package com.salesianostriana.dam.gestionpeliculas.exceptions;

public class DirectorNoEncontradoException extends EntidadNoEncontradaException {
    public DirectorNoEncontradoException(String message) {
        super(message);
    }

    public DirectorNoEncontradoException(Long id){
        super("No se ha encontrado el director con ID: " + id);
    }
}
