package com.salesianostriana.dam.gestionpeliculas.dto;

import com.salesianostriana.dam.gestionpeliculas.model.Director;
import io.swagger.v3.oas.annotations.media.Schema;

public record DirectorRequestDto(
        @Schema(description = "Nombre del Director", example = "Christopher Nolan") String nombre,
        @Schema(description = "Año de nacimiento (Debe ser mayor de edad)", example = "1970") Integer anioNacimiento
) {

    public Director toEntity(){

        return Director.builder()
                .nombre(this.nombre)
                .anioNacimiento(this.anioNacimiento)
                .build();
    }
}
