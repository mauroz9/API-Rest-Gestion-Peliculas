package com.salesianostriana.dam.gestionpeliculas.dto;

import com.salesianostriana.dam.gestionpeliculas.model.Actor;
import com.salesianostriana.dam.gestionpeliculas.model.Pelicula;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

public record PeliculaResponseDto(
        @Schema(description = "Id de la película", example = "1") Long id,
        @Schema(description = "Título de la Película", example = "Oppenheimer") String titulo,
        @Schema(description = "Género de la Película", example = "Drama") String genero,
        @Schema(description = "Fecha de estreno", example = "2023-07-21") LocalDate fechaEstreno,
        @Schema(description = "Director de la Película") DirectorSimpleDto director,
        @Schema(description = "Lista de Actores") List<ActorSimpleDto> actores
) {

    public static PeliculaResponseDto of(Pelicula pelicula) {
        return new PeliculaResponseDto(
                pelicula.getId(),
                pelicula.getTitulo(),
                pelicula.getGenero(),
                pelicula.getFechaEstreno(),
                DirectorSimpleDto.of(pelicula.getDirector()),
                pelicula.getActores().stream().map(ActorSimpleDto::of).toList()
        );
    }
}
