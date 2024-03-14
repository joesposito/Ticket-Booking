package com.trainer.ticketbooking.mapper;

import com.trainer.ticketbooking.dto.StationRequestDto;
import com.trainer.ticketbooking.dto.StationResponseDto;
import com.trainer.ticketbooking.entity.Station;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface StationMapper {
    StationResponseDto stationToDto(Station station);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Station updateStationFromDto(Station station, StationRequestDto stationRequestDto);
}
