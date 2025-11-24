package com.salesianostriana.dam.gestionpeliculas.exceptions;

public class DirectorMenorDeEdadException extends RuntimeException {
    public DirectorMenorDeEdadException(String message) {
        super(message);
    }

    public DirectorMenorDeEdadException(Long id){
        super("El director con ID " + id + " es menor de edad");
    }
}
