package com.example.demo.repositories;

import com.example.demo.entities.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface JeuRepository extends JpaRepository<Jeu, Long> {
        Page<Jeu> findByTitreContains(String titre, Pageable pageable);
        Page<Jeu> findByStudioId(Long studioId, Pageable pageable);
}