package com.salesianostriana.dam.gestionpeliculas.services;

import com.salesianostriana.dam.gestionpeliculas.dto.ActorRequestDto;
import com.salesianostriana.dam.gestionpeliculas.dto.ActorResponseDto;
import com.salesianostriana.dam.gestionpeliculas.exceptions.ActorNoEncontradoException;
import com.salesianostriana.dam.gestionpeliculas.model.Actor;
import com.salesianostriana.dam.gestionpeliculas.repositories.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

    public List<ActorResponseDto> findAll(){
        List<ActorResponseDto> result = actorRepository.findAll().stream().map(ActorResponseDto::of).toList();

        if(result.isEmpty()){
            throw new ActorNoEncontradoException("Lista de actores vacia");
        }

        return result;
    }

    public ActorResponseDto findById(Long id){
        return actorRepository.findById(id).map(ActorResponseDto::of).orElseThrow(() -> new ActorNoEncontradoException(id));
    }

    public ActorResponseDto save(ActorRequestDto dto){
        Actor actorGuardado = actorRepository.save(dto.toEntity());

        return ActorResponseDto.of(actorGuardado);
    }

    public ActorResponseDto modify(Long id, ActorRequestDto dto){
        return actorRepository.findById(id).map(a -> {
            a.setNombre(dto.nombre());
            return actorRepository.save(a);
        }).map(ActorResponseDto::of).orElseThrow(() -> new ActorNoEncontradoException(id));
    }

    public void delete(Long id){
        if(actorRepository.existsById(id)){
            actorRepository.deleteById(id);
        }else{
            throw new ActorNoEncontradoException(id);
        }
    }
}
