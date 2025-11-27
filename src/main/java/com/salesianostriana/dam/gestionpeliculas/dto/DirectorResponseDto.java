package com.salesianostriana.dam.gestionpeliculas.dto;

import com.salesianostriana.dam.gestionpeliculas.model.Director;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record DirectorResponseDto(
        @Schema(description = "Id del Director", example = "1") Long id,
        @Schema(description = "Nombre del Director", example = "Christopher Nolan") String nombre,
        @Schema(description = "Año de nacimiento (Debe ser mauyor de edad)", example = "1970") Integer anioNacimiento,
        @Schema(description = "Lista de películas que ha dirigido") List<PeliculaResponseDto> peliculas
) {

    public static DirectorResponseDto of(Director director){
        return new DirectorResponseDto(
                director.getId(),
                director.getNombre(),
                director.getAnioNacimiento(),
                director.getPeliculas().stream().map(PeliculaResponseDto::of).toList()
        );
    }
}
