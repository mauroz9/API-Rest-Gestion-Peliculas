package com.salesianostriana.dam.gestionpeliculas.model;

import com.salesianostriana.dam.gestionpeliculas.exceptions.DirectorMenorDeEdadException;
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
public class Director {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private Integer anioNacimiento;

    @OneToMany(mappedBy = "director")
    @ToString.Exclude
    private List<Pelicula> peliculas = new ArrayList<>();

    public boolean esMenor(Integer anioEstreno){
        int edad = this.getAnioNacimiento() - anioEstreno;

        if(edad < 18){
            return true;
        }

        return false;
    }

    public boolean esMenor(){
        int edad = this.getAnioNacimiento() - LocalDate.now().getYear();

        if(edad < 18){
            return true;
        }

        return false;
    }
}
