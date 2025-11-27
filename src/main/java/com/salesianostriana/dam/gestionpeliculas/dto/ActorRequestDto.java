package com.salesianostriana.dam.gestionpeliculas.dto;

import com.salesianostriana.dam.gestionpeliculas.model.Actor;
import io.swagger.v3.oas.annotations.media.Schema;

public record ActorRequestDto(
        @Schema(description = "Nombre del Actor", example = "Cillian Murphy") String nombre
) {

    public Actor toEntity(){
        return Actor.builder()
                .nombre(this.nombre)
                .build();
    }
}
