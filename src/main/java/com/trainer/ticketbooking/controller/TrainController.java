package com.trainer.ticketbooking.controller;

import com.trainer.ticketbooking.dto.TrainResponseDto;
import com.trainer.ticketbooking.entity.Train;
import com.trainer.ticketbooking.dto.TrainRequestDto;
import com.trainer.ticketbooking.service.TrainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
public class TrainController {
    private final TrainService trainService;
    public TrainController(TrainService trainService){
        this.trainService = trainService;
    }

    //Creates train object using request body
    @PostMapping("/train")
    public ResponseEntity<TrainResponseDto> createTrain(@RequestBody TrainRequestDto trainRequestDto){
        try {
            TrainResponseDto trainResponseDto = trainService.createTrain(trainRequestDto);
            log.info("New station registered with ID: {}", trainResponseDto.getTrainID());
            return new ResponseEntity<>(trainResponseDto, HttpStatus.CREATED);
        }catch(IllegalArgumentException | NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    //Deletes train object using a train ID in the request path
    @GetMapping("train/{trainID}")
    public ResponseEntity<TrainResponseDto> getTrain(@PathVariable long trainID){
        try {
            TrainResponseDto trainResponseDto = trainService.getTrain(trainID);
            log.info("Train successfully retrieved with ID: {}", trainID);
            return new ResponseEntity<>(trainResponseDto, HttpStatus.OK);
        }catch(NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/train/{trainID}")
    public ResponseEntity<TrainResponseDto> updateTrain(@PathVariable long trainID, @RequestBody TrainRequestDto trainRequestDto){
        try {
            TrainResponseDto trainResponseDto = trainService.updateTrain(trainID, trainRequestDto);
            log.info("Train successfully updated with ID: {}", trainID);
            return new ResponseEntity<>(trainResponseDto, HttpStatus.CREATED);
        }catch(IllegalArgumentException | NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    //Deletes train object using a train ID in the request path
    @DeleteMapping("/train/{trainID}")
    public void deleteTrain(@PathVariable long trainID){
        try {
            trainService.deleteTrain(trainID);
            log.info("Train successfully deleted with ID: {}", trainID);
        }catch(NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}