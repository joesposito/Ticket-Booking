package com.trainer.ticketbooking.service;

import com.trainer.ticketbooking.dto.AddressDto;
import com.trainer.ticketbooking.entity.Address;
import com.trainer.ticketbooking.dto.LocalUserDto;
import com.trainer.ticketbooking.dto.StationCreateDto;
import com.trainer.ticketbooking.mapper.AddressMapper;
import com.trainer.ticketbooking.repo.AddressRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Address createUserAddress(LocalUserDto localUserDto){
        if(localUserDto.getAddressLine1() == null || localUserDto.getCity() == null
                || localUserDto.getCountry() == null || localUserDto.getPostalCode() == null){
            throw new IllegalArgumentException("Could not create address: missing data.");
        }

        Address address = addressMapper.dtoToAddress(localUserDto);
        System.out.println("adres" + address);
        addressRepo.save(address);
        System.out.println(address);
        System.out.println("HERE2!");
        return address;
    }

    //create station address using station data
    public Address createStationAddress(StationCreateDto stationCreateDto){
        if(stationCreateDto.getAddressLine1() == null || stationCreateDto.getCity() == null
                || stationCreateDto.getCountry() == null || stationCreateDto.getPostalCode() == null){
            throw new IllegalArgumentException("Could not create address: missing data.");
        }

        Address address = addressMapper.dtoToAddress(stationCreateDto);
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
