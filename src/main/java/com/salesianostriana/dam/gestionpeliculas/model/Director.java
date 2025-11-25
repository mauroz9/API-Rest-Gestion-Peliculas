package com.salesianostriana.dam.gestionpeliculas.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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

    @OneToMany(mappedBy = "director")
    @ToString.Exclude
    private List<Pelicula> peliculas = new ArrayList<>();
}
