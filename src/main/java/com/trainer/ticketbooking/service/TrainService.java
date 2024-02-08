package com.trainer.ticketbooking.service;

import com.trainer.ticketbooking.dao.StationDao;
import com.trainer.ticketbooking.dao.TrainDao;
import com.trainer.ticketbooking.entity.Station;
import com.trainer.ticketbooking.entity.Train;
import com.trainer.ticketbooking.dto.TrainDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Component
public class TrainService {
    private TrainDao trainDao;
    private StationDao stationDao;


    public TrainService(TrainDao trainDao, StationDao stationDao){
        this.trainDao = trainDao;
        this.stationDao = stationDao;
    }

    //create train and put it in db
    public Train createTrain(TrainDto trainDto) throws IllegalArgumentException{

        //Do not accept trains that have the same departure and arrival stations
        if(trainDto.getDepartureStationID() == trainDto.getArrivalStationID()){
            throw new IllegalArgumentException();
        }

        Train newTrain = new Train();
        Optional<Station> departureStation = stationDao.findByStationID(trainDto.getDepartureStationID());
        Optional<Station> arrivalStation = stationDao.findByStationID(trainDto.getArrivalStationID());

        //if either station does not exist, we throw an exception
        if(departureStation.isEmpty()){
             throw new EntityNotFoundException();
        }

        if(arrivalStation.isEmpty()){
            throw new EntityNotFoundException();
        }

        newTrain.setDepartureStation(departureStation.get());
        newTrain.setArrivalStation(arrivalStation.get());
        trainDao.save(newTrain);
        return newTrain;
    }

    //get train from db
    public Train getTrain(Long trainID) throws EntityNotFoundException {
        Optional<Train> train = trainDao.findByTrainID(trainID);

        if(train.isEmpty()){
            throw new EntityNotFoundException();
        }

        return train.get();
    }

    //update train in db
    @Transactional
    public Train updateTrain(long trainID, TrainDto trainDto) throws EntityNotFoundException {
        Optional<Train> train = trainDao.findByTrainID(trainID);

        if(train.isEmpty()){
            throw new EntityNotFoundException("Train does not exist.");
        }

        Train trainEntity = train.get();

        try {
            trainEntity.setDepartureStation(stationDao.findByStationID(trainDto.getDepartureStationID()).get());
            trainEntity.setArrivalStation(stationDao.findByStationID(trainDto.getArrivalStationID()).get());
        }catch(NoSuchElementException e){
            throw new EntityNotFoundException("One or more stations do not exist.");
        }

        trainDao.save(trainEntity);
        return trainEntity;
    }

    //delete train from db
    public void deleteTrain(Long trainID) throws EntityNotFoundException {
        Optional<Train> train = trainDao.findByTrainID(trainID);

        if(train.isEmpty()){
            throw new EntityNotFoundException();
        }

        trainDao.delete(train.get());
    }
}
