package com.salesianostriana.dam.gestionpeliculas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Director {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private Integer anioNacimiento;

    @OneToMany
    private List<Pelicula> peliculas;
}
