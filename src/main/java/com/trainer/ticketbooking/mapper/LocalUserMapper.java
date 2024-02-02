package com.trainer.ticketbooking.mapper;

import com.trainer.ticketbooking.dto.LocalUserDto;
import com.trainer.ticketbooking.entity.LocalUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LocalUserMapper {
    void updateCustomerFromDto(LocalUserDto dto, @MappingTarget LocalUser user);
}