package com.example.demo.service;

import com.example.demo.dto.JeuCreateDTO;
import com.example.demo.dto.JeuResponseDTO;

import com.example.demo.dto.PageDTO;
import java.util.List;

public interface JeuService {
    JeuResponseDTO create(JeuCreateDTO dto);
    JeuResponseDTO getById(Long id);
    
    PageDTO<JeuResponseDTO> getAllPaginated(int page, int size, String sortBy, String direction);
    JeuResponseDTO update(Long id, JeuCreateDTO dto);
   
    void delete(Long id);
    List<JeuResponseDTO> searchByTitre(String titre);
    
    //gérer ManyToMany
    JeuResponseDTO addTag(Long jeuId, Long genreId);
    JeuResponseDTO removeTag(Long jeuId, Long genreId);
}