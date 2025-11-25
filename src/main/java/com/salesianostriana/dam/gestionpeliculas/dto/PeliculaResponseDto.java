package com.salesianostriana.dam.gestionpeliculas.dto;

import com.salesianostriana.dam.gestionpeliculas.model.Actor;
import com.salesianostriana.dam.gestionpeliculas.model.Pelicula;

import java.time.LocalDate;
import java.util.List;

public record PeliculaResponseDto(Long id, String titulo, String genero, LocalDate fechaEstreno, DirectorSimpleDto director, List<ActorSimpleDto> actores) {

    public PeliculaResponseDto of(Pelicula pelicula){
        return new PeliculaResponseDto(
                pelicula.getId(),
                pelicula.getTitulo(),
                pelicula.getGenero(),
                pelicula.getFechaEstreno(),
                director.of(pelicula.getDirector()),
                pelicula.getActores().stream().map(ActorSimpleDto::of).toList()
        );
    }
}
