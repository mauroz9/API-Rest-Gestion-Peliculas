package com.salesianostriana.dam.gestionpeliculas.dto;

import com.salesianostriana.dam.gestionpeliculas.model.Director;

import java.util.List;

public record DirectorResponseDto(Long id, String nombre, Integer anioNacimiento, List<PeliculaResponseDto> peliculas) {

    public static DirectorResponseDto of(Director director){
        return new DirectorResponseDto(
                director.getId(),
                director.getNombre(),
                director.getAnioNacimiento(),
                director.getPeliculas().stream().map(PeliculaResponseDto::of).toList()
        );
    }
}
