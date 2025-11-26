package com.salesianostriana.dam.gestionpeliculas.repositories;

import com.salesianostriana.dam.gestionpeliculas.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    boolean existsByTitulo(String titulo);
}
