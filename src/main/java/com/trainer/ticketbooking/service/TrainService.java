package com.trainer.ticketbooking.service;

import com.trainer.ticketbooking.repo.StationRepo;
import com.trainer.ticketbooking.repo.TrainDao;
import com.trainer.ticketbooking.entity.Station;
import com.trainer.ticketbooking.entity.Train;
import com.trainer.ticketbooking.dto.TrainDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Component
@Slf4j
public class TrainService {
    private final TrainDao trainDao;
    private final StationRepo stationRepo;


    public TrainService(TrainDao trainDao, StationRepo stationRepo){
        this.trainDao = trainDao;
        this.stationRepo = stationRepo;
    }

    //create train and put it in db
    public Train createTrain(TrainDto trainDto) {

        //Do not accept trains that have the same departure and arrival stations
        if(trainDto.getDepartureStationID() == trainDto.getArrivalStationID()){
            throw new IllegalArgumentException("Departure station and arrival station " +
                    "cannot reference the same station.");
        }

        Train newTrain = new Train();
        Optional<Station> departureStation = stationRepo.findByStationID(trainDto.getDepartureStationID());
        Optional<Station> arrivalStation = stationRepo.findByStationID(trainDto.getArrivalStationID());

        //if either station does not exist, we throw an exception
        if(departureStation.isPresent()){
            newTrain.setDepartureStation(departureStation.get());
        }else{
            throw new NullPointerException
                    ("Station with ID \"" + trainDto.getDepartureStationID() + "\" does not exist.");
        }

        if(arrivalStation.isPresent()){
            newTrain.setArrivalStation(arrivalStation.get());
        }else{
            throw new NullPointerException
                    ("Station with ID \"" + trainDto.getArrivalStationID() + "\" does not exist.");
        }

        trainDao.save(newTrain);
        return newTrain;
    }

    //get train from db
    public Train getTrain(Long trainID) throws EntityNotFoundException {
        Optional<Train> train = trainDao.findByTrainID(trainID);

        if(train.isEmpty()){
            throw new NullPointerException("Train with ID \"" + trainID + "\" does not exist.");
        }

        return train.get();
    }

    //update train in db
    public Train updateTrain(long trainID, TrainDto trainDto) {
        Optional<Train> train = trainDao.findByTrainID(trainID);

        if(train.isEmpty()){
            throw new NullPointerException("Train with ID \"" + trainID + "\" does not exist.");
        }

        Train trainEntity = train.get();

        //Set the departure station
        Optional<Station> departureStation = stationRepo.findByStationID(trainDto.getDepartureStationID());

        if(departureStation.isPresent()){
            trainEntity.setDepartureStation(departureStation.get());
        }else{
            throw new NullPointerException
                    ("Station with ID \"" + trainDto.getDepartureStationID() + "\" does not exist.");
        }

        //Set the arrival station
        Optional<Station> arrivalStation = stationRepo.findByStationID(trainDto.getArrivalStationID());

        if(arrivalStation.isPresent()){
            trainEntity.setArrivalStation(arrivalStation.get());
        }else{
            throw new NullPointerException
                    ("Station with ID \"" + trainDto.getArrivalStationID() + "\" does not exist.");
        }

        //Ensure departure ID != arrival ID
        if(Objects.equals(departureStation.get().getStationID(), arrivalStation.get().getStationID())){
            throw new IllegalArgumentException("Departure station and arrival station " +
                    "cannot reference the same station.");
        }

        trainDao.save(trainEntity);
        return trainEntity;
    }

    //delete train from db
    public void deleteTrain(Long trainID) throws EntityNotFoundException {
        Optional<Train> train = trainDao.findByTrainID(trainID);

        if(train.isEmpty()){
            throw new NullPointerException("Train with ID \"" + trainID + "\" does not exist.");
        }

        trainDao.delete(train.get());
    }
}
