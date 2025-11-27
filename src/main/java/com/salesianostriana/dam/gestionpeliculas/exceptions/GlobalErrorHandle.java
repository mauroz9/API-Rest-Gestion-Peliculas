package com.salesianostriana.dam.gestionpeliculas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class GlobalErrorHandle {

    @ExceptionHandler(EntidadNoEncontradaException.class)
    public ProblemDetail handleEntidadNoEncontrada(EntidadNoEncontradaException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage()
        );

        problemDetail.setTitle("Entidad no encontrada");
        problemDetail.setType(URI.create("https://www.api-peliculas.es/errors/not-found"));

        return problemDetail;
    }

    @ExceptionHandler(PeliculaYaExisteException.class)
    public ProblemDetail handlePeliculaYaExisteException(PeliculaYaExisteException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT, ex.getMessage()
        );

        problemDetail.setTitle("Película duplicada");
        problemDetail.setType(URI.create("https://www.api-peliculas.es/errors/duplicated-movie"));

        return problemDetail;
    }

    @ExceptionHandler(ActorYaEnRepartoException.class)
    public ProblemDetail handleActorYaEnRepartoException(ActorYaEnRepartoException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT, ex.getMessage()
        );

        problemDetail.setTitle("Actor ya asignado");
        problemDetail.setType(URI.create("https://www.api-peliculas.es/errors/actor-assigned"));

        return  problemDetail;
    }

    @ExceptionHandler(DirectorMenorDeEdadException.class)
    public ProblemDetail handleDirectorMenorDeEdadException(DirectorMenorDeEdadException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, ex.getMessage()
        );

        problemDetail.setTitle("Director es menor de edad");
        problemDetail.setType(URI.create("https://www.api-peliculas.es/errors/under-age-director"));

        return problemDetail;
    }

    @ExceptionHandler(DirectorTienePeliculasException.class)
    public ProblemDetail handleDirectorTienePeliculasException(DirectorTienePeliculasException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT, ex.getMessage()
        );

        problemDetail.setTitle("Operación no permitida");
        problemDetail.setType(URI.create("https://www.api-peliculas.es/errors/director-has-movies"));
        return problemDetail;
    }
}
