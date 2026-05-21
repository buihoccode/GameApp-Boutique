package com.example.demo.mapper;

import com.example.demo.dto.GenreDTO;

import com.example.demo.entities.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreDTO toDTO(Genre genre);
    
    @Mapping(target = "jeux", ignore = true)
    Genre toEntity(GenreDTO dto);
    
    List<GenreDTO> toDTOList(List<Genre> genres);
}