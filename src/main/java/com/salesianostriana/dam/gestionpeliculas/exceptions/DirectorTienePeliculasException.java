package com.salesianostriana.dam.gestionpeliculas.exceptions;

public class DirectorTienePeliculasException extends RuntimeException {
    public DirectorTienePeliculasException(String message) {
        super(message);
    }

    public DirectorTienePeliculasException(Long id){
        super("No se puede borrar el director con ID " + id + " porque tiene películas asociadas.");
    }
}
