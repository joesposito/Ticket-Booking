package com.trainer.ticketbooking.mapper;

import com.trainer.ticketbooking.dto.LocalUserDto;
import com.trainer.ticketbooking.entity.LocalUser;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface LocalUserMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateLocalUserFromDto(LocalUserDto dto, @MappingTarget LocalUser localUser);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LocalUser dtoToLocalUser(LocalUserDto dto);
}