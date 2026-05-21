package com.example.demo.service;

import com.example.demo.dto.JeuCreateDTO;
import com.example.demo.dto.JeuResponseDTO;
import java.util.List;

public interface JeuService {
    JeuResponseDTO create(JeuCreateDTO dto);
    JeuResponseDTO getById(Long id);
    JeuResponseDTO update(Long id, JeuCreateDTO dto);
   
    void delete(Long id);
    List<JeuResponseDTO> searchByTitre(String titre);
}