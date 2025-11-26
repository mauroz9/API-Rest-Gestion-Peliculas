package com.salesianostriana.dam.gestionpeliculas.controllers;

import com.salesianostriana.dam.gestionpeliculas.dto.PeliculaRequestDto;
import com.salesianostriana.dam.gestionpeliculas.dto.PeliculaResponseDto;
import com.salesianostriana.dam.gestionpeliculas.services.PeliculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/peliculas")
@RequiredArgsConstructor
public class PeliculaController {

    private final PeliculaService peliculaService;

    @GetMapping
    public ResponseEntity<List<PeliculaResponseDto>> findAll(){
        return ResponseEntity.ok(peliculaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(peliculaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PeliculaResponseDto> create(@RequestBody PeliculaRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeliculaResponseDto> modify(@PathVariable Long id, @RequestBody PeliculaRequestDto dto){
        return ResponseEntity.ok(peliculaService.modify(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        peliculaService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{idPelicula}/actores/{idActor}")
    public ResponseEntity<PeliculaResponseDto> addActor(@PathVariable Long idPelicula, @PathVariable Long idActor){
        return ResponseEntity.ok(peliculaService.addActor(idPelicula, idActor));
    }

}
