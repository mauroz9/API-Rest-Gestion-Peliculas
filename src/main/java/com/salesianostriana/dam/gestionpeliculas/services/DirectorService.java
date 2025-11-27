package com.salesianostriana.dam.gestionpeliculas.services;

import com.salesianostriana.dam.gestionpeliculas.dto.DirectorRequestDto;
import com.salesianostriana.dam.gestionpeliculas.dto.DirectorResponseDto;
import com.salesianostriana.dam.gestionpeliculas.exceptions.DirectorMenorDeEdadException;
import com.salesianostriana.dam.gestionpeliculas.exceptions.DirectorNoEncontradoException;
import com.salesianostriana.dam.gestionpeliculas.model.Director;
import com.salesianostriana.dam.gestionpeliculas.repositories.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;

    public List<DirectorResponseDto> findAll(){
        List<DirectorResponseDto> result = directorRepository.findAll().stream().map(DirectorResponseDto::of).toList();

        if(result.isEmpty()){
            throw new DirectorNoEncontradoException("Lista de directores vacia");
        }

        return result;
    }

    public DirectorResponseDto findById(Long id){
        return directorRepository.findById(id).map(DirectorResponseDto::of).orElseThrow(() -> new DirectorNoEncontradoException(id));
    }

    public DirectorResponseDto save(DirectorRequestDto dto){
        Director guardado = directorRepository.save(dto.toEntity());

        if(guardado.esMenor()){
            throw new DirectorMenorDeEdadException("El director que intentas guardar es menor de edad");
        }

        return DirectorResponseDto.of(guardado);
    }

    public DirectorResponseDto modify(Long id, DirectorRequestDto dto){
        Director director = directorRepository.findById(id).orElseThrow(() -> new DirectorNoEncontradoException(id));

        if(dto.toEntity().esMenor()){
            throw new DirectorMenorDeEdadException("No puedes modificar que el director sea menor de edad");
        }

        director.setNombre(dto.nombre());
        director.setAnioNacimiento(dto.anioNacimiento());

        return DirectorResponseDto.of(director);

    }

    public void delete(Long id){
        if(directorRepository.existsById(id)){
            directorRepository.deleteById(id);
        }else{
            throw new DirectorNoEncontradoException(id);
        }
    }

}
