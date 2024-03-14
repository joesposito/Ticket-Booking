package com.trainer.ticketbooking.mapper;

import com.trainer.ticketbooking.dto.LocalUserRequestDto;
import com.trainer.ticketbooking.dto.LocalUserResponseDto;
import com.trainer.ticketbooking.entity.LocalUser;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface LocalUserMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateLocalUserFromDto(LocalUserRequestDto dto, @MappingTarget LocalUser localUser);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LocalUser dtoToLocalUser(LocalUserRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LocalUserResponseDto localUserToDto(LocalUser localUser);
}