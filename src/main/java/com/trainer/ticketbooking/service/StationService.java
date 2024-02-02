package com.trainer.ticketbooking.service;

import com.trainer.ticketbooking.dao.AddressDao;
import com.trainer.ticketbooking.dao.StationDao;
import com.trainer.ticketbooking.entity.Address;
import com.trainer.ticketbooking.entity.Station;
import com.trainer.ticketbooking.dto.StationCreateDto;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Component
public class StationService {
    @Autowired
    AddressService addressService;
    AddressDao addressDao;
    StationDao stationDao;

    public StationService(StationDao stationDao, AddressDao addressDao){
        this.stationDao = stationDao;
        this.addressDao = addressDao;
    }

    //create station and put it in db
    public Station createStation(StationCreateDto stationCreateDto)
            throws EntityExistsException {
        if(stationDao.findByNameIgnoreCase(stationCreateDto.getName()).isPresent()){
            throw new EntityExistsException();
        }

        Address address = addressService.createStationAddress(stationCreateDto);

        Station newStation = new Station();
        newStation.setAddress(address);
        newStation.setName(stationCreateDto.getName());

        addressDao.save(address);
        stationDao.save(newStation);
        return newStation;
    }

    //get station and put it in db
    public Station getStation(Long stationID) throws EntityNotFoundException {
        Optional<Station> station = stationDao.findByStationID(stationID);

        if(station.isEmpty()){
            throw new EntityNotFoundException();
        }

        return station.get();
    }

    //delete station and put it in db
    public void deleteStation(Long stationID) throws EntityNotFoundException {
        Optional<Station> station = stationDao.findByStationID(stationID);

        if(station.isEmpty()){
            throw new EntityNotFoundException();
        }

        stationDao.delete(station.get());
    }
}

