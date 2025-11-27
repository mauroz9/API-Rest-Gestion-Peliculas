package com.salesianostriana.dam.gestionpeliculas.controllers;

import com.salesianostriana.dam.gestionpeliculas.dto.ActorRequestDto;
import com.salesianostriana.dam.gestionpeliculas.dto.ActorResponseDto;
import com.salesianostriana.dam.gestionpeliculas.services.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/actores")
@RequiredArgsConstructor
@Tag(name = "Actor", description = "Controlador de actores, destinado a realizar opearaciones CRUD")
public class ActorController {

    private final ActorService actorService;

    @GetMapping
    @Operation(summary = "Obtiene todos los actores")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se han encontrado Actores",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ActorResponseDto.class)),
                            examples = @ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "id": 1, 
                                                    "nombre": "Brad Pitt", 
                                                    "peliculas": []
                                                },
                                                {
                                                    "id": 2, 
                                                    "nombre": "Cillian Murphy", 
                                                    "peliculas": []
                                                }
                                            ]
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se ha encontrado ningún actor",
                    content = @Content
            )
    })
    public ResponseEntity<List<ActorResponseDto>> findAll() {
        return ResponseEntity.ok(actorService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un actor indicando su ID")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Se ha encontrado el Actor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ActorResponseDto.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "id": 1, 
                                                "nombre": "Brad Pitt", 
                                                "peliculas": []
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se ha encontrado el Actor",
                    content = @Content
            )
    })
    public ResponseEntity<ActorResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(actorService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crea un Actor")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Actor creado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ActorResponseDto.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "id": 3, 
                                                "nombre": "Robert Downey Jr.", 
                                                "peliculas": []
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request, datos erroneos",
                    content = @Content
            )
    })
    public ResponseEntity<ActorResponseDto> create(@RequestBody ActorRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(actorService.save(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar un actor")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Actor modificado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ActorResponseDto.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "id": 2, 
                                                "nombre": "Mauro Murphy", 
                                                "peliculas": []
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Actor no encontrado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request, datos erroneos",
                    content = @Content
            )
    })
    public ResponseEntity<ActorResponseDto> modify(@PathVariable Long id, @RequestBody ActorRequestDto dto) {
        return ResponseEntity.ok(actorService.modify(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un Actor")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Actor borrado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Actor no encontrado",
                    content = @Content
            )
    })
    public ResponseEntity<?> delete(@PathVariable Long id) {
        actorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
