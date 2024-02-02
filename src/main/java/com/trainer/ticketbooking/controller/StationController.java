package com.trainer.ticketbooking.controller;

import com.trainer.ticketbooking.dto.StationCreateDto;
import com.trainer.ticketbooking.entity.Station;
import com.trainer.ticketbooking.service.StationService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class StationController {
    @Autowired
    StationService stationService;

    //Creates station using request body
    @PostMapping("/create-station")
    public void createStation(@RequestBody StationCreateDto stationCreateDto){
        try {
            stationService.createStation(stationCreateDto);
            log.info("New station registered with name: {}", stationCreateDto.getName());
        }catch(EntityExistsException e){
            log.error("Station name already exists: {}", stationCreateDto.getName());
        }
    }

    //Gets station object using a station ID in the request path
    @GetMapping("/get-station/{stationID}")
    public Station getStation(@PathVariable long stationID){
        try {
            Station station = stationService.getStation(stationID);
            log.info("Station successfully retrieved with ID: {}", stationID);
            return station;
        }catch(EntityNotFoundException e){
            log.error("Station ID does not exist: {}", stationID);
            return null;
        }
    }

    //Deletes station object using a station ID in the request path
    @DeleteMapping("/delete-station/{stationID}")
    public void deleteTrain(@PathVariable long stationID){
        try {
            stationService.deleteStation(stationID);
            log.info("Station successfully deleted with ID: {}", stationID);
        }catch(EntityNotFoundException e){
            log.error("Station ID does not exist: {}", stationID);
        }
    }
}

