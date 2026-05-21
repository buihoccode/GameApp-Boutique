package com.example.demo.service;

import com.example.demo.dto.JeuCreateDTO;
import com.example.demo.dto.JeuResponseDTO;
import com.example.demo.entities.Jeu;
import com.example.demo.entities.Studio;
import com.example.demo.mapper.JeuMapper;
import com.example.demo.repositories.JeuRepository;
import com.example.demo.repositories.StudioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class JeuServiceImpl implements JeuService {

    private final JeuRepository jeuRepository;
    private final StudioRepository studioRepository;
    private final JeuMapper jeuMapper;

    public JeuServiceImpl(JeuRepository jeuRepository, StudioRepository studioRepository, JeuMapper jeuMapper) {
        this.jeuRepository = jeuRepository;
        this.studioRepository = studioRepository;
        this.jeuMapper = jeuMapper;
    }

    @Override
    public JeuResponseDTO create(JeuCreateDTO dto) {
        Studio studio = studioRepository.findById(dto.getStudioId())
                .orElseThrow(() -> new RuntimeException("Studio introuvable"));

        Jeu jeu = jeuMapper.toEntity(dto);
        jeu.setStudio(studio);
        return jeuMapper.toDto(jeuRepository.save(jeu));
    }

    @Override
    @Transactional(readOnly = true)
    public JeuResponseDTO getById(Long id) {
        Jeu jeu = jeuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jeu introuvable"));
        return jeuMapper.toDto(jeu);
    }

    @Override
    public JeuResponseDTO update(Long id, JeuCreateDTO dto) {
        Jeu existing = jeuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jeu introuvable"));
        
        jeuMapper.updateEntity(dto, existing);
        
        if(dto.getStudioId() != null) {
             Studio studio = studioRepository.findById(dto.getStudioId())
                .orElseThrow(() -> new RuntimeException("Studio introuvable"));
             existing.setStudio(studio);
        }
        return jeuMapper.toDto(jeuRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!jeuRepository.existsById(id)) {
            throw new RuntimeException("Jeu introuvable");
        }
        jeuRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<JeuResponseDTO> searchByTitre(String titre) {
        if (titre == null || titre.trim().isEmpty()) {
             return List.of(); 
        }
        return null; // Search logic will be fully implemented in Part 3 with Pagination
    }
}