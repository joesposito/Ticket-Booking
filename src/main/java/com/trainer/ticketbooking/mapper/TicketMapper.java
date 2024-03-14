package com.trainer.ticketbooking.mapper;

import com.trainer.ticketbooking.dto.TicketRequestDto;
import com.trainer.ticketbooking.dto.TicketResponseDto;
import com.trainer.ticketbooking.entity.Ticket;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Ticket updateTicketFromDto(TicketRequestDto ticketRequestDto, @MappingTarget Ticket ticket);

    TicketResponseDto ticketToDto(Ticket ticket);
}
