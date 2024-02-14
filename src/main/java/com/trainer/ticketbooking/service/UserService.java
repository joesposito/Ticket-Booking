package com.trainer.ticketbooking.service;

import com.trainer.ticketbooking.dao.AddressDao;
import com.trainer.ticketbooking.dao.LocalUserDao;
import com.trainer.ticketbooking.dto.TrainDto;
import com.trainer.ticketbooking.entity.Address;
import com.trainer.ticketbooking.entity.LocalUser;
import com.trainer.ticketbooking.dto.LocalUserDto;
import com.trainer.ticketbooking.entity.Train;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Component
public class UserService {
    @Autowired
    AddressService addressService;
    LocalUserDao localUserDao;
    AddressDao addressDao;

    public UserService(LocalUserDao localUserDao, AddressDao addressDao){
        this.localUserDao = localUserDao;
        this.addressDao = addressDao;
    }

    //create user and put it in db
    public LocalUser createUser(LocalUserDto localUserDto) throws EntityExistsException {
        if(localUserDao.findByUsernameIgnoreCase(localUserDto.getUsername()).isPresent()){
            throw new EntityExistsException();
        }

        Address address = addressService.createUserAddress(localUserDto);

        if(address == null){
            throw new IllegalArgumentException("Could not create address: missing data.");
        }

        LocalUser newUser = new LocalUser();
        newUser.setUsername(localUserDto.getUsername());
        newUser.setPassword(localUserDto.getPassword());
        newUser.setFirstName(localUserDto.getFirstName());
        newUser.setLastName(localUserDto.getLastName());
        newUser.setAddress(address);

        System.out.println(newUser);

        addressDao.save(address);
        localUserDao.save(newUser);
        return newUser;
    }

    //get user and put it in db
    public LocalUser getUser(Long userID) throws EntityNotFoundException {
        Optional<LocalUser> localUser = localUserDao.findByUserID(userID);

        if(localUser.isEmpty()){
            throw new EntityNotFoundException();
        }

        return localUser.get();
    }

    public LocalUser updateUser(long userID, LocalUserDto localUserDto) throws EntityNotFoundException {
        Optional<LocalUser> localUser = localUserDao.findByUserID(userID);

        if(localUser.isEmpty()){
            throw new EntityNotFoundException("User does not exist.");
        }

        LocalUser localUserEntity = localUser.get();
        Address address = addressService.createUserAddress(localUserDto);

        try {
            localUserEntity.setFirstName(localUserDto.getFirstName());
            localUserEntity.setLastName(localUserDto.getLastName());
            localUserEntity.setPassword(localUserDto.getPassword());

            if(address != null){
                addressDao.save(address);
                localUserEntity.setAddress(address);
            }
        }catch(NoSuchElementException e){
            throw new EntityNotFoundException("One or more stations do not exist.");
        }

        localUserDao.save(localUserEntity);
        return localUserEntity;
    }


    //delete user and put it in db
    public void deleteUser(Long userID) throws EntityNotFoundException {
        Optional<LocalUser> localUser = localUserDao.findByUserID(userID);

        if(localUser.isEmpty()){
            throw new EntityNotFoundException();
        }

        localUserDao.delete(localUser.get());
    }
}
