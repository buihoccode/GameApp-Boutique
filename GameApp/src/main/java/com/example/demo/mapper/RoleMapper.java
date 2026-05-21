package com.example.demo.mapper;

import com.example.demo.dto.RoleDTO;

import com.example.demo.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO toDTO(Role role);
    
    @Mapping(target = "users", ignore = true)
    Role toEntity(RoleDTO dto);
    
    List<RoleDTO> toDTOList(List<Role> roles);
}