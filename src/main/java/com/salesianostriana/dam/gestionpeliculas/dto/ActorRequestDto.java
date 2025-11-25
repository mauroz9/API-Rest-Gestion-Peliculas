package com.salesianostriana.dam.gestionpeliculas.dto;

import com.salesianostriana.dam.gestionpeliculas.model.Actor;

public record ActorRequestDto(String nombre) {

    public Actor toEntity(){
        return Actor.builder()
                .nombre(this.nombre)
                .build();
    }
}
