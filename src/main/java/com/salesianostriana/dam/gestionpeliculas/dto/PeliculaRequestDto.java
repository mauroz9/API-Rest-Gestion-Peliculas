package com.salesianostriana.dam.gestionpeliculas.dto;

import com.salesianostriana.dam.gestionpeliculas.model.Pelicula;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record PeliculaRequestDto(
        @Schema(description = "Título de la Película", example = "Oppenheimer") String titulo,
        @Schema(description = "Género de la Película", example = "Drama") String genero,
        @Schema(description = "Fecha de estreno", example = "2023-07-21") LocalDate fechaEstreno,
        @Schema(description = "Id del Director de la Película", example = "1") Long idDirector
) {

    public Pelicula toEntity(){
        return Pelicula.builder()
                .titulo(this.titulo)
                .genero(this.genero)
                .fechaEstreno(this.fechaEstreno)
                .build();
    }
}
