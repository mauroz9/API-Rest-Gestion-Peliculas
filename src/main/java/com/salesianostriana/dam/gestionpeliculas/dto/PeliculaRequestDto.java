package com.salesianostriana.dam.gestionpeliculas.dto;

import com.salesianostriana.dam.gestionpeliculas.model.Pelicula;

import java.time.LocalDate;

public record PeliculaRequestDto(String titulo, String genero, LocalDate fechaEstreno, Long idDirector) {

    public Pelicula toEntity(){
        return Pelicula.builder()
                .titulo(this.titulo)
                .genero(this.genero)
                .fechaEstreno(this.fechaEstreno)
                .build();
    }
}
