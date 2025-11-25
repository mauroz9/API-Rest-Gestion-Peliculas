package com.salesianostriana.dam.gestionpeliculas.dto;

import com.salesianostriana.dam.gestionpeliculas.model.Director;

public record DirectorSimpleDto(Long id, String nombre) {

    public static DirectorSimpleDto of(Director director){
        return new DirectorSimpleDto(
                director.getId(),
                director.getNombre()
        );
    }
}
