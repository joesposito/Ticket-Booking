package com.trainer.ticketbooking.controller;

import com.trainer.ticketbooking.entity.Train;
import com.trainer.ticketbooking.dto.TrainDto;
import com.trainer.ticketbooking.service.TrainService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class TrainController {
    @Autowired
    TrainService trainService;

    //Creates train object using request body
    @PostMapping("/create-train")
    public void createTrain(@RequestBody TrainDto trainDto){
        try {
            Train newTrain = trainService.createTrain(trainDto);
            log.info("New station registered with ID: {}", newTrain.getTrainID());
        }catch(EntityNotFoundException e){
            //TODO
            log.error("Station ID does not exist");
        }catch(IllegalArgumentException e){
            log.error("Both the departure station and arrival station have the same IDs: {}, {}",
                    trainDto.getDepartureStationID(), trainDto.getArrivalStationID());
        }
    }

    //Deletes train object using a train ID in the request path
    @GetMapping("/get-train/{trainID}")
    public Train getTrain(@PathVariable long trainID){
        try {
            Train train = trainService.getTrain(trainID);
            log.info("Train successfully retrieved with ID: {}", trainID);
            return train;
        }catch(EntityNotFoundException e){
            log.error("Train ID does not exist: {}", trainID);
            return null;
        }
    }

    //Deletes train object using a train ID in the request path
    @DeleteMapping("/delete-train/{trainID}")
    public void deleteTrain(@PathVariable long trainID){
        try {
            trainService.deleteTrain(trainID);
            log.info("Train successfully deleted with ID: {}", trainID);
        }catch(EntityNotFoundException e){
            log.error("Train ID does not exist: {}", trainID);
        }
    }
}