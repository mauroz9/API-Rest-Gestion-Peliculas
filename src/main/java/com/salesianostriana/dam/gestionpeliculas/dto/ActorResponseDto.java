package com.salesianostriana.dam.gestionpeliculas.dto;

import com.salesianostriana.dam.gestionpeliculas.model.Actor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record ActorResponseDto(
        @Schema(description = "Id del actor", example = "1") Long id,
        @Schema(description = "Nombre del Actor", example = "Cillian Murphy") String nombre,
        @Schema(description = "Lista de películas que las que ha participado") List<PeliculaResponseDto> peliculas
) {

    public static ActorResponseDto of(Actor actor){
        return new ActorResponseDto(
                actor.getId(),
                actor.getNombre(),
                actor.getPeliculas().stream().map(PeliculaResponseDto::of).toList()
        );
    }
}
