package com.salesianostriana.dam.gestionpeliculas.exceptions;

public class ActorYaEnRepartoException extends RuntimeException {
    public ActorYaEnRepartoException(String message) {
        super(message);
    }

    public ActorYaEnRepartoException(Long id){
        super("El actor con ID " + id + " ya está en el reparto");
    }
}
