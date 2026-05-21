package com.example.demo.mapper;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.User;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})

public interface UserMapper {

	UserDTO toDTO(User user);

    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toEntity(UserDTO dto);

    List<UserDTO> toDTOList(List<User> users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    void updateFromDTO(UserDTO dto, @MappingTarget User user);
}