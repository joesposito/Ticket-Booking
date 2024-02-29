package com.trainer.ticketbooking.mapper;

import com.trainer.ticketbooking.dto.AddressDto;
import com.trainer.ticketbooking.dto.LocalUserDto;
import com.trainer.ticketbooking.dto.StationCreateDto;
import com.trainer.ticketbooking.entity.Address;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAddressFromDto(AddressDto dto, @MappingTarget Address address);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAddressFromDto(LocalUserDto dto, @MappingTarget Address address);

    Address dtoToAddress(LocalUserDto localUserDto);
    Address dtoToAddress(StationCreateDto stationCreateDto);
}
