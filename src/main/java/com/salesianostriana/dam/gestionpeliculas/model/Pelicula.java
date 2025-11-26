package com.salesianostriana.dam.gestionpeliculas.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pelicula {

    @Id
    @GeneratedValue
    private Long id;

    private String titulo;
    private String genero;
    private LocalDate fechaEstreno;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "director_id"))
    private Director director;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "actores_pelicula",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @ToString.Exclude
    private List<Actor> actores = new ArrayList<>();

    //METODOS HELPER
    public void addDirector(Director director){
        this.setDirector(director);
        if(director.getPeliculas().isEmpty()){
            director.setPeliculas(new ArrayList<>());
        }
        director.getPeliculas().add(this);
    }
}
