package com.salesianostriana.dam.gestionpeliculas.controllers;

import com.salesianostriana.dam.gestionpeliculas.dto.PeliculaRequestDto;
import com.salesianostriana.dam.gestionpeliculas.dto.PeliculaResponseDto;
import com.salesianostriana.dam.gestionpeliculas.services.PeliculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.xml.bind.annotation.XmlElementDecl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/peliculas")
@RequiredArgsConstructor
@Tag(name = "Peliculas", description = "Controlador de peliculas, destinada a realizar operaciones CRUD")
public class PeliculaController {

    private final PeliculaService peliculaService;

    @GetMapping
    @Operation(summary = "Obtener todas las películas")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Se han encontrado Películas",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PeliculaResponseDto.class)),
                            examples = @ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "id": 1,
                                                    "titulo": "Oppenheimer",
                                                    "genero": "Biopic",
                                                    "fechaEstreno": "2023-07-21",
                                                    "director": {
                                                        "id": 1,
                                                        "nombre": "Christopher Nolan"
                                                    },
                                                    "actores": [
                                                        {
                                                            "id": 1,
                                                            "nombre": "Cillian Murphy"
                                                        }
                                                    ]
                                                }
                                            ]
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se ha encontrado ninguna Película",
                    content = @Content
            )
    })
    public ResponseEntity<List<PeliculaResponseDto>> findAll() {
        return ResponseEntity.ok(peliculaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una Película indicando su ID")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Se ha encontrado la Película",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PeliculaResponseDto.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "titulo": "Oppenheimer",
                                                "genero": "Biopic",
                                                "fechaEstreno": "2023-07-21",
                                                "director": {
                                                    "id": 1,
                                                    "nombre": "Christopher Nolan"
                                                },
                                                "actores": []
                                            }
                                            """
                            )
                    )
            )
    })
    public ResponseEntity<PeliculaResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(peliculaService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crea una Película")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Película creada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PeliculaResponseDto.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "id": 2,
                                                "titulo": "Inception",
                                                "genero": "Sci-Fi",
                                                "fechaEstreno": "2010-07-16",
                                                "director": {
                                                    "id": 1,
                                                    "nombre": "Christopher Nolan"
                                                },
                                                "actores": []
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request, datos erroneos o Director menor de edad",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Director no encontrado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "El título de la película ya existe",
                    content = @Content
            )
    })
    public ResponseEntity<PeliculaResponseDto> create(@RequestBody PeliculaRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaService.save(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar una Película")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Película modificada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PeliculaResponseDto.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "titulo": "Oppenheimer (Extended Cut)",
                                                "genero": "Biopic",
                                                "fechaEstreno": "2023-07-21",
                                                "director": {
                                                    "id": 1,
                                                    "nombre": "Christopher Nolan"
                                                },
                                                "actores": []
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request, datos erroneos o Director menor de edad",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Película o Director no encontrado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "El título de la Película ya existe",
                    content = @Content
            )
    })
    public ResponseEntity<PeliculaResponseDto> modify(@PathVariable Long id, @RequestBody PeliculaRequestDto dto) {
        return ResponseEntity.ok(peliculaService.modify(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una Película")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Película Borrada",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Película no encontrada",
                    content = @Content
            )
    })
    public ResponseEntity<?> delete(@PathVariable Long id) {
        peliculaService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{idPelicula}/actores/{idActor}")
    @Operation(summary = "Añadir un Actor a una Película")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Actor asignado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PeliculaResponseDto.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "titulo": "Oppenheimer",
                                                "genero": "Biopic",
                                                "fechaEstreno": "2023-07-21",
                                                "director": {
                                                    "id": 1,
                                                    "nombre": "Christopher Nolan"
                                                },
                                                "actores": [
                                                    {
                                                        "id": 1,
                                                        "nombre": "Cillian Murphy"
                                                    }
                                                ]
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Película o actor no encontrados",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "El actor ya estaba asignado",
                    content = @Content
            )
    })
    public ResponseEntity<PeliculaResponseDto> addActor(@PathVariable Long idPelicula, @PathVariable Long idActor) {
        return ResponseEntity.ok(peliculaService.addActor(idPelicula, idActor));
    }

}
