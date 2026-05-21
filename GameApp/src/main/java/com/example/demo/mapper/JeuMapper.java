package com.example.demo.mapper;

import com.example.demo.dto.JeuCreateDTO;
import com.example.demo.dto.JeuResponseDTO;
import com.example.demo.entities.Jeu;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {GenreMapper.class})
public interface JeuMapper {

    @Mapping(target = "tags", source = "genres")
    @Mapping(target = "studioDisplay", expression = "java(formatStudioDisplay(jeu))")
    @Mapping(target = "prixTotal", expression = "java(calculerPrixTotal(jeu))")
    JeuResponseDTO toDto(Jeu jeu);

    @Mapping(target = "studio.id", source = "studioId")
    @Mapping(target = "genres", ignore = true) 
    Jeu toEntity(JeuCreateDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(JeuCreateDTO dto, @MappingTarget Jeu jeu);

    default String formatStudioDisplay(Jeu jeu) {
        if (jeu.getStudio() == null) return null;
        return jeu.getStudio().getNom() + " " + jeu.getStudio().getSiege();
    }

    default Double calculerPrixTotal(Jeu jeu) {
        if (jeu == null || jeu.getPrix() == null || jeu.getNbLicence() == null || jeu.getIndie() == null) {
            return 0.0;
        }
        return jeu.getIndie() ? (jeu.getPrix() * jeu.getNbLicence() / 2.0) : (jeu.getPrix() * jeu.getNbLicence());
    }
}