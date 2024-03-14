package com.trainer.ticketbooking.service;

import com.trainer.ticketbooking.entity.Address;
import com.trainer.ticketbooking.dto.LocalUserRequestDto;
import com.trainer.ticketbooking.dto.StationRequestDto;
import com.trainer.ticketbooking.mapper.AddressMapper;
import com.trainer.ticketbooking.repo.AddressRepo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddressService {
    private final AddressMapper addressMapper;
    private final AddressRepo addressRepo;

    public AddressService(AddressMapper addressMapper, AddressRepo addressRepo){
        this.addressMapper = addressMapper;
        this.addressRepo = addressRepo;
    }

    //create address object using local user data
    public Address createUserAddress(LocalUserRequestDto localUserRequestDto){
        if(localUserRequestDto.getAddressLine1() == null || localUserRequestDto.getCity() == null
                || localUserRequestDto.getCountry() == null || localUserRequestDto.getPostalCode() == null){
            throw new IllegalArgumentException("Could not create address: missing data.");
        }

        Address address = addressMapper.dtoToAddress(localUserRequestDto);
        addressRepo.save(address);
        return address;
    }

    //create station address using station data
    public Address createStationAddress(StationRequestDto stationRequestDto){
        if(stationRequestDto.getAddressLine1() == null || stationRequestDto.getCity() == null
                || stationRequestDto.getCountry() == null || stationRequestDto.getPostalCode() == null){
            throw new IllegalArgumentException("Could not create address: missing data.");
        }

        Address address = addressMapper.dtoToAddress(stationRequestDto);
        addressRepo.save(address);
        return address;
    }

    //get address from db
    public Address getAddress(Long addressID) {
        Optional<Address> address = addressRepo.findByAddressID(addressID);

        if(address.isEmpty()){
            throw new NullPointerException("Address with ID \"" + addressID + "\" does not exist.");
        }

        return address.get();
    }

    //delete address from db
    public void deleteAddress(Long addressID) {
        Optional<Address> address = addressRepo.findByAddressID(addressID);

        if(address.isEmpty()){
            throw new NullPointerException("Address with ID \"" + addressID + "\" does not exist.");
        }

        addressRepo.delete(address.get());
    }
}
