package com.salesianostriana.dam.gestionpeliculas.services;

import com.salesianostriana.dam.gestionpeliculas.dto.PeliculaRequestDto;
import com.salesianostriana.dam.gestionpeliculas.dto.PeliculaResponseDto;
import com.salesianostriana.dam.gestionpeliculas.exceptions.*;
import com.salesianostriana.dam.gestionpeliculas.model.Actor;
import com.salesianostriana.dam.gestionpeliculas.model.Director;
import com.salesianostriana.dam.gestionpeliculas.model.Pelicula;
import com.salesianostriana.dam.gestionpeliculas.repositories.ActorRepository;
import com.salesianostriana.dam.gestionpeliculas.repositories.DirectorRepository;
import com.salesianostriana.dam.gestionpeliculas.repositories.PeliculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;
    private final DirectorRepository directorRepository;
    private final DirectorService directorService;
    private final ActorRepository actorRepository;

    public List<PeliculaResponseDto> findAll() {
        List<PeliculaResponseDto> result = peliculaRepository.findAll().stream().map(PeliculaResponseDto::of).toList();

        if(result.isEmpty()){
            throw new PeliculaNoEncontradaException("Lista de películas vacia");
        }

        return result;
    }

    public PeliculaResponseDto findById(Long id) {
        return peliculaRepository.findById(id).map(PeliculaResponseDto::of).orElseThrow(() -> new PeliculaNoEncontradaException(id));
    }

    public PeliculaResponseDto save(PeliculaRequestDto dto) {
        Director director = directorRepository.findById(dto.idDirector()).orElseThrow(() -> new DirectorNoEncontradoException(dto.idDirector()));

        if(peliculaRepository.existsByTitulo(dto.titulo())){
            throw new PeliculaYaExisteException(dto.titulo());
        }

        directorService.detectarMenor(director, dto.fechaEstreno().getYear());

        Pelicula pelicula = dto.toEntity();
        pelicula.addDirector(director);

        return PeliculaResponseDto.of(peliculaRepository.save(pelicula));
    }

    public PeliculaResponseDto modify(Long id, PeliculaRequestDto dto) {
        Pelicula pelicula = peliculaRepository.findById(id).orElseThrow(() -> new PeliculaNoEncontradaException(id));
        Director director = directorRepository.findById(dto.idDirector()).orElseThrow(() -> new DirectorNoEncontradoException(dto.idDirector()));

        directorService.detectarMenor(director, dto.fechaEstreno().getYear());

        if(peliculaRepository.existsByTitulo(dto.titulo()) && !pelicula.getTitulo().equals(dto.titulo())){
            throw new PeliculaYaExisteException(dto.titulo());
        }

        pelicula.setTitulo(dto.titulo());
        pelicula.setGenero(dto.genero());
        pelicula.setFechaEstreno(dto.fechaEstreno());
        pelicula.addDirector(director);

        return PeliculaResponseDto.of(pelicula);
    }

    public void delete(Long id) {
        if (peliculaRepository.existsById(id)) {
            peliculaRepository.deleteById(id);
        } else {
            throw new PeliculaNoEncontradaException(id);
        }
    }

    public PeliculaResponseDto addActor(Long peliculaId, Long actorId){
        Pelicula pelicula = peliculaRepository.findById(peliculaId).orElseThrow(() -> new PeliculaNoEncontradaException(peliculaId));
        Actor actor = actorRepository.findById(actorId).orElseThrow(() -> new ActorNoEncontradoException(actorId));

        boolean asignado = pelicula.getActores().stream().anyMatch(a -> a.getId().equals(actorId));

        if(asignado){
            throw new ActorYaEnRepartoException(actorId);
        }
        
        pelicula.addActor(actor);

        return PeliculaResponseDto.of(peliculaRepository.save(pelicula));
    }
}