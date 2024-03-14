package com.trainer.ticketbooking.service;

import com.trainer.ticketbooking.dto.TrainResponseDto;
import com.trainer.ticketbooking.mapper.TrainMapper;
import com.trainer.ticketbooking.repo.StationRepo;
import com.trainer.ticketbooking.repo.TrainRepo;
import com.trainer.ticketbooking.entity.Station;
import com.trainer.ticketbooking.entity.Train;
import com.trainer.ticketbooking.dto.TrainRequestDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Component
@Slf4j
public class TrainService {
    private final TrainRepo trainRepo;
    private final StationRepo stationRepo;
    private final TrainMapper trainMapper;

    public TrainService(TrainRepo trainRepo, StationRepo stationRepo, TrainMapper trainMapper){
        this.trainRepo = trainRepo;
        this.stationRepo = stationRepo;
        this.trainMapper = trainMapper;
    }

    //create train and put it in db
    public TrainResponseDto createTrain(TrainRequestDto trainRequestDto) {

        //Do not accept trains that have the same departure and arrival stations
        if(trainRequestDto.getDepartureStationID() == trainRequestDto.getArrivalStationID()){
            throw new IllegalArgumentException("Departure station and arrival station " +
                    "cannot reference the same station.");
        }

        Train newTrain = new Train();
        Optional<Station> departureStation = stationRepo.findByStationID(trainRequestDto.getDepartureStationID());
        Optional<Station> arrivalStation = stationRepo.findByStationID(trainRequestDto.getArrivalStationID());

        //if either station does not exist, we throw an exception
        if(departureStation.isPresent()){
            newTrain.setDepartureStation(departureStation.get());
        }else{
            throw new NullPointerException
                    ("Station with ID \"" + trainRequestDto.getDepartureStationID() + "\" does not exist.");
        }

        if(arrivalStation.isPresent()){
            newTrain.setArrivalStation(arrivalStation.get());
        }else{
            throw new NullPointerException
                    ("Station with ID \"" + trainRequestDto.getArrivalStationID() + "\" does not exist.");
        }

        trainRepo.save(newTrain);
        return trainMapper.trainToDto(newTrain);
    }

    //get train from db
    public TrainResponseDto getTrain(Long trainID) throws EntityNotFoundException {
        Optional<Train> train = trainRepo.findByTrainID(trainID);

        if(train.isEmpty()){
            throw new NullPointerException("Train with ID \"" + trainID + "\" does not exist.");
        }

        return trainMapper.trainToDto(train.get());
    }

    //update train in db
    public TrainResponseDto updateTrain(long trainID, TrainRequestDto trainRequestDto) {
        Optional<Train> optionalTrain = trainRepo.findByTrainID(trainID);

        if(optionalTrain.isEmpty()){
            throw new NullPointerException("Train with ID \"" + trainID + "\" does not exist.");
        }

        Train train = optionalTrain.get();

        //Set the departure station
        Optional<Station> departureStation = stationRepo.findByStationID(trainRequestDto.getDepartureStationID());

        if(departureStation.isPresent()){
            train.setDepartureStation(departureStation.get());
        }else{
            throw new NullPointerException
                    ("Station with ID \"" + trainRequestDto.getDepartureStationID() + "\" does not exist.");
        }

        //Set the arrival station
        Optional<Station> arrivalStation = stationRepo.findByStationID(trainRequestDto.getArrivalStationID());

        if(arrivalStation.isPresent()){
            train.setArrivalStation(arrivalStation.get());
        }else{
            throw new NullPointerException
                    ("Station with ID \"" + trainRequestDto.getArrivalStationID() + "\" does not exist.");
        }

        //Ensure departure ID != arrival ID
        if(Objects.equals(departureStation.get().getStationID(), arrivalStation.get().getStationID())){
            throw new IllegalArgumentException("Departure station and arrival station " +
                    "cannot reference the same station.");
        }

        trainRepo.save(train);
        return trainMapper.trainToDto(train);
    }

    //delete train from db
    public void deleteTrain(Long trainID) throws EntityNotFoundException {
        Optional<Train> train = trainRepo.findByTrainID(trainID);

        if(train.isEmpty()){
            throw new NullPointerException("Train with ID \"" + trainID + "\" does not exist.");
        }

        trainRepo.delete(train.get());
    }
}
