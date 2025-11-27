package com.salesianostriana.dam.gestionpeliculas.controllers;

import com.salesianostriana.dam.gestionpeliculas.dto.DirectorRequestDto;
import com.salesianostriana.dam.gestionpeliculas.dto.DirectorResponseDto;
import com.salesianostriana.dam.gestionpeliculas.services.DirectorService;
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
@RequestMapping("/api/v1/directores")
@RequiredArgsConstructor
@Tag(name = "Directores", description = "Controlador de directores, destinado a realizar opearaciones CRUD")
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping
    @Operation(summary = "Obtener todos los directores")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Se han encontrado Directores",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DirectorResponseDto.class)),
                            examples = @ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "id": 1,
                                                    "nombre": "Christopher Nolan",
                                                    "anioNacimiento": 1970,
                                                    "peliculas": []
                                                },
                                                {
                                                    "id": 2,
                                                    "nombre": "Steven Spielberg",
                                                    "anioNacimiento": 1946,
                                                    "peliculas": []
                                                }
                                            ]
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No sea ha encontrado ningún director",
                    content = @Content
            )
    })
    public ResponseEntity<List<DirectorResponseDto>> findAll() {
        return ResponseEntity.ok(directorService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obteiene un director indicando su ID")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Se ha encontrado el Director",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DirectorResponseDto.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "nombre": "Christopher Nolan",
                                                "anioNacimiento": 1970,
                                                "peliculas": []
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se ha encontrado el Director",
                    content = @Content
            )
    })
    public ResponseEntity<DirectorResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(directorService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crea un Director")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Director Creado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DirectorResponseDto.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "id": 3,
                                                "nombre": "Quentin Tarantino",
                                                "anioNacimiento": 1963,
                                                "peliculas": []
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request, datos erroneos o Director menor de edad",
                    content = @Content
            )
    })
    public ResponseEntity<DirectorResponseDto> create(@RequestBody DirectorRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(directorService.save(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar un Director")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Director modificado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DirectorResponseDto.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "nombre": "Christopher Edward Nolan",
                                                "anioNacimiento": 1970,
                                                "peliculas": []
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Director no encontrado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request, datos erroneos o Director menor de edad"
            )
    })
    public ResponseEntity<DirectorResponseDto> modify(@PathVariable Long id, @RequestBody DirectorRequestDto dto) {
        return ResponseEntity.ok(directorService.modify(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un Director")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Director borrado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Director no encontrado",
                    content = @Content
            )
    })
    public ResponseEntity<?> delete(@PathVariable Long id) {
        directorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
