package com.salesianostriana.dam.gestionpeliculas.exceptions;

public class ActorNoEncontradoException extends EntidadNoEncontradaException {
    public ActorNoEncontradoException(String message) {
        super(message);
    }

    public ActorNoEncontradoException(Long id){
        super("No se ha encontrado el actor con ID: " + id);
    }
}
