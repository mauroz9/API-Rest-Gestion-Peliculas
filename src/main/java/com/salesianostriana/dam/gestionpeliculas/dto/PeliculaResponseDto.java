package com.salesianostriana.dam.gestionpeliculas.dto;

import com.salesianostriana.dam.gestionpeliculas.model.Pelicula;

import java.time.LocalDate;

public record PeliculaResponseDto(Long id, String titulo, String genero, LocalDate fechaEstreno) {

    public PeliculaResponseDto of(Pelicula pelicula){
        return new PeliculaResponseDto(
                pelicula.getId(),
                pelicula.getTitulo(),
                pelicula.getGenero(),
                pelicula.getFechaEstreno()
        );
    }
}
