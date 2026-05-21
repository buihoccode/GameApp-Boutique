package com.example.demo.controller;

import com.example.demo.dto.JeuCreateDTO;
import com.example.demo.dto.JeuResponseDTO;
import com.example.demo.dto.PageDTO;
import com.example.demo.dto.GameResponse;
import com.example.demo.service.JeuService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jeux")
public class JeuController {

    private final JeuService jeuService;

    public JeuController(JeuService jeuService) {
        this.jeuService = jeuService;
    }

    // Pagination exacte comme dans le TP5
    @GetMapping
    public ResponseEntity<GameResponse<PageDTO<JeuResponseDTO>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(GameResponse.ok(jeuService.getAllPaginated(page, size, sortBy, direction)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponse<JeuResponseDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(GameResponse.ok(jeuService.getById(id)));
    }

    //Creation
    @PostMapping
    public ResponseEntity<GameResponse<JeuResponseDTO>> create(@Valid @RequestBody JeuCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GameResponse.created("Jeu créé avec succès", jeuService.create(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameResponse<JeuResponseDTO>> update(@PathVariable Long id, @Valid @RequestBody JeuCreateDTO dto) {
        return ResponseEntity.ok(GameResponse.ok("Jeu mis à jour", jeuService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GameResponse<Void>> delete(@PathVariable Long id) {
        jeuService.delete(id);
        return ResponseEntity.ok(GameResponse.ok("Jeu supprimé", null));
    }

    //Recherche
    @GetMapping("/search")
    public ResponseEntity<GameResponse<List<JeuResponseDTO>>> search(@RequestParam String titre) {
        return ResponseEntity.ok(GameResponse.ok(jeuService.searchByTitre(titre)));
    }

    //Endpoints spécifiques pour gérer les Tags (ManyToMany)
    @PostMapping("/{id}/tags/{tagId}")
    public ResponseEntity<GameResponse<JeuResponseDTO>> addTag(@PathVariable Long id, @PathVariable Long tagId) {
        return ResponseEntity.ok(GameResponse.ok(jeuService.addTag(id, tagId)));
    }

    @DeleteMapping("/{id}/tags/{tagId}")
    public ResponseEntity<GameResponse<JeuResponseDTO>> removeTag(@PathVariable Long id, @PathVariable Long tagId) {
        return ResponseEntity.ok(GameResponse.ok(jeuService.removeTag(id, tagId)));
    }
}