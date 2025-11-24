package com.salesianostriana.dam.gestionpeliculas.dto;

import com.salesianostriana.dam.gestionpeliculas.model.Director;

public record DirectorResponseDto(Long id, String nombre, Integer anioNacimiento) {

    public DirectorResponseDto of(Director director){
        return new DirectorResponseDto(
                director.getId(),
                director.getNombre(),
                director.getAnioNacimiento()
        );
    }
}
