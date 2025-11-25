package com.salesianostriana.dam.gestionpeliculas.services;

import com.salesianostriana.dam.gestionpeliculas.dto.DirectorRequestDto;
import com.salesianostriana.dam.gestionpeliculas.dto.DirectorResponseDto;
import com.salesianostriana.dam.gestionpeliculas.exceptions.ActorNoEncontradoException;
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

    public List<DirectorResponseDto> getAll(){
        List<DirectorResponseDto> result = directorRepository.findAll().stream().map(DirectorResponseDto::of).toList();

        /*if(result.isEmpty()){
            throw new DirectorNoEncontradoException("Lista de directores vacia");
        }*/

        return result;
    }

    public DirectorResponseDto getById(Long id){
        return directorRepository.findById(id).map(DirectorResponseDto::of).orElseThrow(() -> new DirectorNoEncontradoException(id));
    }

    public DirectorResponseDto save(DirectorRequestDto dto){
        Director guardado = directorRepository.save(dto.toEntity());

        return DirectorResponseDto.of(guardado);
    }

    public DirectorResponseDto modify(Long id, DirectorRequestDto dto){
        return directorRepository.findById(id).map(d ->{
            d.setNombre(dto.nombre());
            d.setAnioNacimiento(dto.anioNacimiento());
            return directorRepository.save(d);
        }).map(DirectorResponseDto::of).orElseThrow(() -> new DirectorNoEncontradoException(id));
    }

    public void delete(Long id){
        if(directorRepository.existsById(id)){
            directorRepository.deleteById(id);
        }else{
            throw new DirectorNoEncontradoException(id);
        }
    }

}
