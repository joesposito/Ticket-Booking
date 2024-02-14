package com.trainer.ticketbooking.service;

import com.trainer.ticketbooking.dao.AddressDao;
import com.trainer.ticketbooking.entity.Address;
import com.trainer.ticketbooking.dto.LocalUserDto;
import com.trainer.ticketbooking.dto.StationCreateDto;
import com.trainer.ticketbooking.entity.Train;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddressService {
    private AddressDao addressDao;

    public AddressService(AddressDao addressDao){
        this.addressDao = addressDao;
    }

    //create address object using local user data
    public Address createUserAddress(LocalUserDto localUserDto){
        if(localUserDto.getAddressLine1() == null || localUserDto.getCity() == null
                || localUserDto.getCountry() == null || localUserDto.getPostalCode() == null){
            return null;
        }

        Address address = new Address();
        address.setAddressLine1(localUserDto.getAddressLine1());
        address.setAddressLine2(localUserDto.getAddressLine2());
        address.setCity(localUserDto.getCity());
        address.setCountry(localUserDto.getCountry());
        address.setPostal_code(localUserDto.getPostalCode());
        return address;
    }

    //create station address using station data
    public Address createStationAddress(StationCreateDto stationCreateDto){
        if(stationCreateDto.getAddressLine1() == null || stationCreateDto.getCity() == null
                || stationCreateDto.getCountry() == null || stationCreateDto.getPostalCode() == null){
            return null;
        }

        Address address = new Address();
        address.setAddressLine1(stationCreateDto.getAddressLine1());
        address.setCity(stationCreateDto.getCity());
        address.setCountry(stationCreateDto.getCountry());
        address.setPostal_code(stationCreateDto.getPostalCode());
        return address;
    }

    //get address from db
    public Address getAddress(Long addressID) throws EntityNotFoundException {
        Optional<Address> address = addressDao.findByAddressID(addressID);

        if(address.isEmpty()){
            throw new EntityNotFoundException();
        }

        return address.get();
    }


    //delete address from db
    public void deleteAddress(Long addressID) throws EntityNotFoundException {
        Optional<Address> address = addressDao.findByAddressID(addressID);

        if(address.isEmpty()){
            throw new EntityNotFoundException();
        }

        addressDao.delete(address.get());
    }
}
