package com.trainer.ticketbooking.controller;

import com.trainer.ticketbooking.dto.StationRequestDto;
import com.trainer.ticketbooking.dto.StationResponseDto;
import com.trainer.ticketbooking.service.StationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<StationResponseDto> createStation(@RequestBody StationRequestDto stationRequestDto){
        try {
            StationResponseDto stationResponseDto = stationService.createStation(stationRequestDto);
            log.info("New station registered with name: {}", stationRequestDto.getName());
            return new ResponseEntity<>(stationResponseDto, HttpStatus.CREATED);
        }catch(IllegalArgumentException | NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    //Gets station object using a station ID in the request path
    @GetMapping("/station/{stationID}")
    public ResponseEntity<StationResponseDto> getStation(@PathVariable long stationID){
        try {
            StationResponseDto stationResponseDto = stationService.getStation(stationID);
            log.info("Station successfully retrieved with ID: {}", stationID);
            return new ResponseEntity<>(stationResponseDto, HttpStatus.OK);
        }catch(NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/station/{stationID}")
    public ResponseEntity<StationResponseDto> updateStation(@PathVariable long stationID,
                                                            @RequestBody StationRequestDto stationRequestDto){
        try{
            StationResponseDto stationResponseDto = stationService.updateStation(stationID, stationRequestDto);
            log.info("Station successfully updated with ID: {}", stationID);
            return new ResponseEntity<>(stationResponseDto, HttpStatus.OK);
        }catch(NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    //Deletes station object using a station ID in the request path
    @DeleteMapping("/station/{stationID}")
    public void deleteStation(@PathVariable long stationID){
        try {
            stationService.deleteStation(stationID);
            log.info("Station successfully deleted with ID: {}", stationID);
        }catch(NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}

