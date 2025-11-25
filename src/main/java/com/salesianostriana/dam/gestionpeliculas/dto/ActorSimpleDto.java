package com.salesianostriana.dam.gestionpeliculas.dto;

import com.salesianostriana.dam.gestionpeliculas.model.Actor;

public record ActorSimpleDto(Long id,String nombre) {

    public static ActorSimpleDto of(Actor actor){
        return new ActorSimpleDto(
                actor.getId(),
                actor.getNombre()
        );
    }
}
