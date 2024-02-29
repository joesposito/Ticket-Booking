package com.trainer.ticketbooking.service;

import com.trainer.ticketbooking.repo.AddressRepo;
import com.trainer.ticketbooking.repo.StationRepo;
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
    private final AddressService addressService;
    private final AddressRepo addressRepo;
    private final StationRepo stationRepo;

    public StationService(AddressService addressService, StationRepo stationRepo, AddressRepo addressRepo){
        this.addressService = addressService;
        this.stationRepo = stationRepo;
        this.addressRepo = addressRepo;
    }

    //create station and put it in db
    public Station createStation(StationCreateDto stationCreateDto) {
        if(stationRepo.findByNameIgnoreCase(stationCreateDto.getName()).isPresent()){
            throw new IllegalArgumentException
                    ("Station with name \"" + stationCreateDto.getName() + "\" already exists.");
        }

        Address address = addressService.createStationAddress(stationCreateDto);

        Station newStation = new Station();
        newStation.setAddress(address);
        newStation.setName(stationCreateDto.getName());

        addressRepo.save(address);
        stationRepo.save(newStation);
        return newStation;
    }

    //get station and put it in db
    public Station getStation(Long stationID) {
        Optional<Station> station = stationRepo.findByStationID(stationID);

        if(station.isEmpty()){
            throw new NullPointerException
                    ("Station with ID \"" + stationID + "\" does not exist.");
        }

        return station.get();
    }

    //delete station and put it in db
    public void deleteStation(Long stationID) {
        Optional<Station> station = stationRepo.findByStationID(stationID);

        if(station.isEmpty()){
            throw new NullPointerException
                    ("Station with ID \"" + stationID + "\" does not exist.");
        }

        stationRepo.delete(station.get());
    }
}

