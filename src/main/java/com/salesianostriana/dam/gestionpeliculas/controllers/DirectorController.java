package com.salesianostriana.dam.gestionpeliculas.controllers;

import com.salesianostriana.dam.gestionpeliculas.dto.DirectorRequestDto;
import com.salesianostriana.dam.gestionpeliculas.dto.DirectorResponseDto;
import com.salesianostriana.dam.gestionpeliculas.services.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/directores")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping
    public ResponseEntity<List<DirectorResponseDto>> findAll(){
        return ResponseEntity.ok(directorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(directorService.getById(id));
    }

    @PostMapping
    public ResponseEntity<DirectorResponseDto> create(@RequestBody DirectorRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(directorService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorResponseDto> modify(@PathVariable Long id, @RequestBody DirectorRequestDto dto){
        return ResponseEntity.ok(directorService.modify(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        directorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
