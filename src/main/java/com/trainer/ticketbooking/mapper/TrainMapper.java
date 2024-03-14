package com.trainer.ticketbooking.mapper;

import com.trainer.ticketbooking.dto.TrainResponseDto;
import com.trainer.ticketbooking.entity.Train;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrainMapper {
    TrainResponseDto trainToDto(Train train);
}
