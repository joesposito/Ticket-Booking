package com.trainer.ticketbooking.controller;

import com.trainer.ticketbooking.entity.Train;
import com.trainer.ticketbooking.dto.TrainDto;
import com.trainer.ticketbooking.service.TrainService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public void createTrain(@RequestBody TrainDto trainDto){
        try {
            Train newTrain = trainService.createTrain(trainDto);
            log.info("New station registered with ID: {}", newTrain.getTrainID());
        }catch(IllegalArgumentException | NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    //Deletes train object using a train ID in the request path
    @GetMapping("train/{trainID}")
    public Train getTrain(@PathVariable long trainID){
        try {
            Train train = trainService.getTrain(trainID);
            log.info("Train successfully retrieved with ID: {}", trainID);
            return train;
        }catch(NullPointerException e){
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/train/{trainID}")
    public void updateTrain(@PathVariable long trainID, @RequestBody TrainDto trainDto){
        try {
            trainService.updateTrain(trainID, trainDto);
            log.info("Train successfully updated with ID: {}", trainID);
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