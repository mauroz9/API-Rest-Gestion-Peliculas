package com.salesianostriana.dam.gestionpeliculas.dto;

import com.salesianostriana.dam.gestionpeliculas.model.Director;
import io.swagger.v3.oas.annotations.media.Schema;

public record DirectorSimpleDto(
        @Schema(description = "Id del Director", example = "1") Long id,
        @Schema(description = "Nombre del Director", example = "Christopher Nolan") String nombre
) {

    public static DirectorSimpleDto of(Director director){
        return new DirectorSimpleDto(
                director.getId(),
                director.getNombre()
        );
    }
}
