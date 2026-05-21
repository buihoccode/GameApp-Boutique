package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entities.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.JeuMapper;
import com.example.demo.repositories.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class JeuServiceImpl implements JeuService {

    private final JeuRepository jeuRepository;
    private final StudioRepository studioRepository;
    private final GenreRepository genreRepository; // NOUVEAU
    private final JeuMapper jeuMapper;

    public JeuServiceImpl(JeuRepository jeuRepository, StudioRepository studioRepository, GenreRepository genreRepository, JeuMapper jeuMapper) {
        this.jeuRepository = jeuRepository;
        this.studioRepository = studioRepository;
        this.genreRepository = genreRepository;
        this.jeuMapper = jeuMapper;
    }

    @Override
    public JeuResponseDTO create(JeuCreateDTO dto) {
        Studio studio = studioRepository.findById(dto.getStudioId())
                .orElseThrow(() -> new ResourceNotFoundException("Studio introuvable"));

        Jeu jeu = jeuMapper.toEntity(dto);
        jeu.setStudio(studio);
        return jeuMapper.toDto(jeuRepository.save(jeu));
    }

    @Override
    @Transactional(readOnly = true)
    public JeuResponseDTO getById(Long id) {
        Jeu jeu = jeuRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Jeu introuvable"));
        return jeuMapper.toDto(jeu);
    }

    @Override
    @Transactional(readOnly = true)
    public PageDTO<JeuResponseDTO> getAllPaginated(int page, int size, String sortBy, String dir) {
        Sort sort = dir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Page<Jeu> result = jeuRepository.findAll(PageRequest.of(page, size, sort));
        
        List<JeuResponseDTO> dtos = result.getContent().stream().map(jeuMapper::toDto).collect(Collectors.toList());
        return new PageDTO<>(dtos, result.getNumber(), result.getSize(), result.getTotalElements(), result.getTotalPages(), result.isFirst(), result.isLast());
    }

    @Override
    public JeuResponseDTO update(Long id, JeuCreateDTO dto) {
        Jeu existing = jeuRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Jeu introuvable"));
        jeuMapper.updateEntity(dto, existing);
        
        if(dto.getStudioId() != null) {
             Studio studio = studioRepository.findById(dto.getStudioId()).orElseThrow(() -> new ResourceNotFoundException("Studio introuvable"));
             existing.setStudio(studio);
        }
        return jeuMapper.toDto(jeuRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!jeuRepository.existsById(id)) throw new ResourceNotFoundException("Jeu introuvable");
        jeuRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<JeuResponseDTO> searchByTitre(String titre) {
        if (titre == null || titre.trim().isEmpty()) {
             return List.of();
        }
        return jeuRepository.findByTitreContains(titre, PageRequest.of(0, 20)).getContent()
                .stream().map(jeuMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public JeuResponseDTO addTag(Long jeuId, Long genreId) {
        Jeu jeu = jeuRepository.findById(jeuId).orElseThrow(() -> new ResourceNotFoundException("Jeu introuvable"));
        Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new ResourceNotFoundException("Genre introuvable"));
        if (!jeu.getGenres().contains(genre)) jeu.getGenres().add(genre);
        return jeuMapper.toDto(jeuRepository.save(jeu));
    }

    @Override
    public JeuResponseDTO removeTag(Long jeuId, Long genreId) {
        Jeu jeu = jeuRepository.findById(jeuId).orElseThrow(() -> new ResourceNotFoundException("Jeu introuvable"));
        Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new ResourceNotFoundException("Genre introuvable"));
        jeu.getGenres().remove(genre);
        return jeuMapper.toDto(jeuRepository.save(jeu));
    }
}