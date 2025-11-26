package com.salesianostriana.dam.gestionpeliculas.controllers;

import com.salesianostriana.dam.gestionpeliculas.dto.ActorRequestDto;
import com.salesianostriana.dam.gestionpeliculas.dto.ActorResponseDto;
import com.salesianostriana.dam.gestionpeliculas.services.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/actores")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @GetMapping
    public ResponseEntity<List<ActorResponseDto>> findAll(){
        return ResponseEntity.ok(actorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(actorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ActorResponseDto> create(@RequestBody ActorRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(actorService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActorResponseDto> modify(@PathVariable Long id, @RequestBody ActorRequestDto dto){
        return ResponseEntity.ok(actorService.modify(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        actorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
