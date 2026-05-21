package com.example.demo.mapper;

import com.example.demo.dto.StudioDTO;
import com.example.demo.entities.Studio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StudioMapper {
    StudioDTO toDto(Studio studio);
    
    @Mapping(target = "jeux", ignore = true)
    Studio toEntity(StudioDTO dto);
    
    List<StudioDTO> toDTOList(List<Studio> studios);
}