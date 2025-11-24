package com.salesianostriana.dam.gestionpeliculas.dto;

import com.salesianostriana.dam.gestionpeliculas.model.Actor;

public record ActorResponseDto(Long id, String nombre) {

    public ActorResponseDto of(Actor actor){
        return new ActorResponseDto(
                actor.getId(),
                actor.getNombre()
        );
    }
}
