package com.salesianostriana.dam.gestionpeliculas.dto;

import com.salesianostriana.dam.gestionpeliculas.model.Director;

public record DirectorRequestDto(String nombre, Integer anioNacimiento) {

    public Director toEntity(){

        return Director.builder()
                .nombre(this.nombre)
                .anioNacimiento(this.anioNacimiento)
                .build();
    }
}
