package com.trainer.ticketbooking.controller;

import com.trainer.ticketbooking.dto.TicketRequestDto;
import com.trainer.ticketbooking.dto.TicketResponseDto;
import com.trainer.ticketbooking.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @PostMapping("/ticket/{quantity}")
    public ResponseEntity<List<TicketResponseDto>> createTicket(@PathVariable int quantity,
                                                                @RequestBody TicketRequestDto ticketRequestDto){
        try {
            ArrayList<TicketResponseDto> tickets = new ArrayList<>(quantity);

            for(int i = 0; i < quantity; i++){
                tickets.add(ticketService.createTicket(ticketRequestDto));
            }

            log.info("Ticket(s) successfully created.");
            return new ResponseEntity<>(tickets, HttpStatus.CREATED);
        }catch(NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/ticket/{ticketID}")
    public ResponseEntity<TicketResponseDto> getTicket(@PathVariable long ticketID){
        try{
            TicketResponseDto newTicketRequestDto = ticketService.getTicket(ticketID);
            log.info("Ticket successfully retrieved with ID: {}", ticketID);
            return new ResponseEntity<>(newTicketRequestDto, HttpStatus.OK);
        }catch(NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/tickets/{trainID}")
    public ResponseEntity<List<TicketResponseDto>> getAllTickets(@PathVariable long trainID){
        try{
            List<TicketResponseDto> ticketResponseDtoList = ticketService.getAllTickets(trainID);
            log.info("Ticket(s) successfully retrieved.");
            return new ResponseEntity<>(ticketResponseDtoList, HttpStatus.OK);
        }catch(NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/ticket/{ticketID}")
    public ResponseEntity<TicketResponseDto> updateTicket(@PathVariable long ticketID, @RequestBody TicketRequestDto ticketRequestDto){
        try{
            TicketResponseDto newTicketRequestDto = ticketService.updateTicket(ticketID, ticketRequestDto);
            log.info("Ticket(s) successfully updated.");
            return new ResponseEntity<>(newTicketRequestDto, HttpStatus.OK);
        }catch(NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/ticket/{ticketID}")
    public void deleteTicket(@PathVariable long ticketID){
        try{
            ticketService.getTicket(ticketID);
            log.info("Ticket successfully deleted with ID: {}", ticketID);
        }catch(NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
