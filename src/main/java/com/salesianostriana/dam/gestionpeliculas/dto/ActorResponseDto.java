package com.salesianostriana.dam.gestionpeliculas.dto;

import com.salesianostriana.dam.gestionpeliculas.model.Actor;

import java.util.List;

public record ActorResponseDto(Long id, String nombre, List<PeliculaResponseDto> peliculas) {

    public static ActorResponseDto of(Actor actor){
        return new ActorResponseDto(
                actor.getId(),
                actor.getNombre(),
                actor.getPeliculas().stream().map(PeliculaResponseDto::of).toList()
        );
    }
}
