package com.salesianostriana.dam.gestionpeliculas.repositories;

import com.salesianostriana.dam.gestionpeliculas.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
}
