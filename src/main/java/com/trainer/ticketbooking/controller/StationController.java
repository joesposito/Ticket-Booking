package com.trainer.ticketbooking.controller;

import com.trainer.ticketbooking.dto.StationCreateDto;
import com.trainer.ticketbooking.entity.Station;
import com.trainer.ticketbooking.service.StationService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
public class StationController {
    private final StationService stationService;
    public StationController(StationService stationService){
        this.stationService = stationService;
    }

    //Creates station using request body
    @PostMapping("/station")
    public void createStation(@RequestBody StationCreateDto stationCreateDto){
        try {
            stationService.createStation(stationCreateDto);
            log.info("New station registered with name: {}", stationCreateDto.getName());
        }catch(IllegalArgumentException | NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    //Gets station object using a station ID in the request path
    @GetMapping("/station/{stationID}")
    public Station getStation(@PathVariable long stationID){
        try {
            Station station = stationService.getStation(stationID);
            log.info("Station successfully retrieved with ID: {}", stationID);
            return station;
        }catch(NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    //Deletes station object using a station ID in the request path
    @DeleteMapping("/station/{stationID}")
    public void deleteTrain(@PathVariable long stationID){
        try {
            stationService.deleteStation(stationID);
            log.info("Station successfully deleted with ID: {}", stationID);
        }catch(NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}

