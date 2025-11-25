package com.salesianostriana.dam.gestionpeliculas.services;

import com.salesianostriana.dam.gestionpeliculas.dto.PeliculaRequestDto;
import com.salesianostriana.dam.gestionpeliculas.dto.PeliculaResponseDto;
import com.salesianostriana.dam.gestionpeliculas.exceptions.PeliculaNoEncontradaException;
import com.salesianostriana.dam.gestionpeliculas.model.Pelicula;
import com.salesianostriana.dam.gestionpeliculas.repositories.PeliculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    public List<PeliculaResponseDto> findAll() {
        List<PeliculaResponseDto> result = peliculaRepository.findAll().stream().map(PeliculaResponseDto::of).toList();

        /*if(result.isEmpty()){
            throw new PeliculaNoEncontradaException("Lista de películas vacia");
        }*/

        return result;
    }

    public PeliculaResponseDto findById(Long id) {
        return peliculaRepository.findById(id).map(PeliculaResponseDto::of).orElseThrow(() -> new PeliculaNoEncontradaException(id));
    }

    public PeliculaResponseDto save(PeliculaRequestDto dto) {
        return PeliculaResponseDto.of(peliculaRepository.save(dto.toEntity()));
    }

    public PeliculaResponseDto modify(Long id, PeliculaRequestDto dto) {
        Pelicula pelicula = peliculaRepository.findById(id).orElseThrow(() -> new PeliculaNoEncontradaException(id));

        pelicula.setTitulo(dto.titulo());
        pelicula.setGenero(dto.genero());
        pelicula.setFechaEstreno(dto.fechaEstreno());

        return PeliculaResponseDto.of(pelicula);
    }

    public void delete(Long id) {
        if (peliculaRepository.existsById(id)) {
            peliculaRepository.deleteById(id);
        } else {
            throw new PeliculaNoEncontradaException(id);
        }
    }
}