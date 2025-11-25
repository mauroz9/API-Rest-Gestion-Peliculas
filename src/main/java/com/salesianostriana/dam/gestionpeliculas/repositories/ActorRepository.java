package com.salesianostriana.dam.gestionpeliculas.repositories;

import com.salesianostriana.dam.gestionpeliculas.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
