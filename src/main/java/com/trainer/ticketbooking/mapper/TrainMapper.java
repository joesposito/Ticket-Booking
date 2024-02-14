package com.trainer.ticketbooking.mapper;

import com.trainer.ticketbooking.dto.TrainDto;
import com.trainer.ticketbooking.entity.Train;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface TrainMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTrainFromDto(TrainDto dto, @MappingTarget Train train);

}
