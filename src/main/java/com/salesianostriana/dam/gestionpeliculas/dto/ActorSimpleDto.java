package com.salesianostriana.dam.gestionpeliculas.dto;

import com.salesianostriana.dam.gestionpeliculas.model.Actor;
import io.swagger.v3.oas.annotations.media.Schema;

public record ActorSimpleDto(
        @Schema(description = "Id del Actor", example = "1") Long id,
        @Schema(description = "Nombre del Actor") String nombre
) {

    public static ActorSimpleDto of(Actor actor){
        return new ActorSimpleDto(
                actor.getId(),
                actor.getNombre()
        );
    }
}
