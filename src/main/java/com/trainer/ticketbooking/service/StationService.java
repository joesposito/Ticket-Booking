package com.trainer.ticketbooking.service;

import com.trainer.ticketbooking.dto.StationResponseDto;
import com.trainer.ticketbooking.mapper.StationMapper;
import com.trainer.ticketbooking.repo.AddressRepo;
import com.trainer.ticketbooking.repo.StationRepo;
import com.trainer.ticketbooking.entity.Address;
import com.trainer.ticketbooking.entity.Station;
import com.trainer.ticketbooking.dto.StationRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Component
public class StationService {
    private final AddressService addressService;
    private final AddressRepo addressRepo;
    private final StationRepo stationRepo;
    private final StationMapper stationMapper;


    public StationService(AddressService addressService, StationRepo stationRepo, AddressRepo addressRepo,
                          StationMapper stationMapper){
        this.addressService = addressService;
        this.stationRepo = stationRepo;
        this.addressRepo = addressRepo;
        this.stationMapper = stationMapper;
    }

    //create station and put it in db
    public StationResponseDto createStation(StationRequestDto stationRequestDto) {
        if(stationRepo.findByNameIgnoreCase(stationRequestDto.getName()).isPresent()){
            throw new IllegalArgumentException
                    ("Station with name \"" + stationRequestDto.getName() + "\" already exists.");
        }

        Address address = addressService.createStationAddress(stationRequestDto);

        Station newStation = new Station();
        newStation.setAddress(address);
        newStation.setName(stationRequestDto.getName());

        addressRepo.save(address);
        stationRepo.save(newStation);
        return stationMapper.stationToDto(newStation);
    }

    //get station and put it in db
    public StationResponseDto getStation(Long stationID) {
        Optional<Station> station = stationRepo.findByStationID(stationID);

        if(station.isEmpty()){
            throw new NullPointerException
                    ("Station with ID \"" + stationID + "\" does not exist.");
        }

        return stationMapper.stationToDto(station.get());
    }

    //updates station in db
    public StationResponseDto updateStation(long stationID, StationRequestDto stationRequestDto) {
        Optional<Station> stationOptional = stationRepo.findByStationID(stationID);

        if(stationOptional.isEmpty()){
            throw new NullPointerException("Station with ID \"" + stationID + "\" does not exist.");
        }

        Station station = stationMapper.updateStationFromDto(stationOptional.get(), stationRequestDto);
        stationRepo.save(station);
        return stationMapper.stationToDto(station);
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

