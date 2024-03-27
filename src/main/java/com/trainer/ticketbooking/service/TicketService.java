package com.trainer.ticketbooking.service;

import com.trainer.ticketbooking.dto.TicketRequestDto;
import com.trainer.ticketbooking.dto.TicketResponseDto;
import com.trainer.ticketbooking.entity.Train;
import com.trainer.ticketbooking.entity.enums.SeatClass;
import com.trainer.ticketbooking.entity.Ticket;
import com.trainer.ticketbooking.mapper.TicketMapper;
import com.trainer.ticketbooking.repo.StationRepo;
import com.trainer.ticketbooking.repo.TicketRepo;
import com.trainer.ticketbooking.repo.TrainRepo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class TicketService {
    private final TicketRepo ticketRepo;
    private final TicketMapper ticketMapper;
    private final TrainRepo trainRepo;
    private final StationRepo stationRepo;

    public TicketService(TicketRepo ticketRepo, TicketMapper ticketMapper, TrainRepo trainRepo,
                         StationRepo stationRepo){
        this.ticketRepo = ticketRepo;
        this.ticketMapper = ticketMapper;
        this.trainRepo = trainRepo;
        this.stationRepo = stationRepo;
    }

    public TicketResponseDto createTicket(TicketRequestDto ticketRequestDto){
        try{
            Ticket ticket = new Ticket();
            ticket.setSeatClass(SeatClass.valueOf(ticketRequestDto.getSeatClass().toUpperCase()));
            ticket.setCost(ticketRequestDto.getCost());

            Optional<Train> trainOptional = trainRepo.findByTrainID(ticketRequestDto.getTrainID());
            if(trainOptional.isEmpty()){
                throw new NullPointerException("Train with ID \"" + ticketRequestDto.getTrainID() + "\" does not exist.");
            }
            ticket.setTrain(trainOptional.get());
            //TODO: Implement numbering system.
            ticket.setSeatNumber((int) (Math.random() * 100000));

            ticketRepo.save(ticket);
            return ticketMapper.ticketToDto(ticket);
        }catch(NullPointerException e){
            return null;
        }
    }

    public TicketResponseDto getTicket(long ticketID){
        Optional<Ticket> ticketOptional = ticketRepo.findById(ticketID);

        if(ticketOptional.isEmpty()){
            throw new NullPointerException("Ticket with ID \"" + ticketID + "\" does not exist.");
        }

        return ticketMapper.ticketToDto(ticketOptional.get());
    }

    public List<TicketResponseDto> getAllTickets(long trainID){
        Optional<Train> optionalTrain = trainRepo.findByTrainID(trainID);
        if(optionalTrain.isEmpty()){
            throw new NullPointerException("Train with ID \"" + trainID + "\" does not exist.");
        }

        List<Ticket> ticketList = optionalTrain.get().getTickets();
        List<TicketResponseDto> ticketResponseDtoList = new ArrayList<>();
        ticketList.forEach(ticket -> ticketResponseDtoList.add(ticketMapper.ticketToDto(ticket)));

        return ticketResponseDtoList;
    }

    public TicketResponseDto updateTicket(long ticketID, TicketRequestDto ticketRequestDto){
        Optional<Ticket> ticketOptional = ticketRepo.findById(ticketID);

        if(ticketOptional.isEmpty()){
            throw new NullPointerException("Ticket with ID \"" + ticketID + "\" does not exist.");
        }

        Ticket ticket = ticketMapper.updateTicketFromDto(ticketRequestDto, ticketOptional.get());
        ticketRepo.save(ticket);
        return ticketMapper.ticketToDto(ticket);
    }

    public void deleteTicket(long ticketID){
        Optional<Ticket> ticketOptional = ticketRepo.findById(ticketID);

        if(ticketOptional.isEmpty()){
            throw new NullPointerException("Ticket with ID \"" + ticketID + "\" does not exist.");
        }

        ticketRepo.delete(ticketOptional.get());
    }
}
