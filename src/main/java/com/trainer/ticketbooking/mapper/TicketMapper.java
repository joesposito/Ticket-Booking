package com.trainer.ticketbooking.mapper;

import com.trainer.ticketbooking.dto.TicketRequestDto;
import com.trainer.ticketbooking.dto.TicketResponseDto;
import com.trainer.ticketbooking.entity.Ticket;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Ticket updateTicketFromDto(TicketRequestDto ticketRequestDto, @MappingTarget Ticket ticket);

    TicketResponseDto ticketToDto(Ticket ticket);

    //since mapstruct presumably doesn't know how to find train id by looking through the train entity,
    //we can just do that manually with aftermapping
    @AfterMapping
    private static void setTrain(Ticket ticket, @MappingTarget TicketResponseDto ticketResponseDto){
        ticketResponseDto.setTrainID(ticket.getTrain().getTrainID());
    }
}
