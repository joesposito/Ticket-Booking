package com.trainer.ticketbooking.service;

import com.trainer.ticketbooking.mapper.AddressMapper;
import com.trainer.ticketbooking.mapper.LocalUserMapper;
import com.trainer.ticketbooking.repo.AddressRepo;
import com.trainer.ticketbooking.repo.LocalUserRepo;
import com.trainer.ticketbooking.entity.Address;
import com.trainer.ticketbooking.entity.LocalUser;
import com.trainer.ticketbooking.dto.LocalUserDto;
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
    private final AddressService addressService;
    private final LocalUserMapper localUserMapper;
    private final AddressMapper addressMapper;
    private final LocalUserRepo localUserRepo;
    private final AddressRepo addressRepo;

    public UserService(AddressService addressService, LocalUserRepo localUserRepo, AddressRepo addressRepo,
                       AddressMapper addressMapper, LocalUserMapper localUserMapper){
        this.addressService = addressService;
        this.localUserRepo = localUserRepo;
        this.addressRepo = addressRepo;
        this.addressMapper = addressMapper;
        this.localUserMapper = localUserMapper;
    }

    //create user and put it in db
    public LocalUser createUser(LocalUserDto localUserDto) {
        if(localUserRepo.findByUsernameIgnoreCase(localUserDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException
                    ("User with username \"" + localUserDto.getUsername() + "\" already exists.");
        }

        //address service will throw NPE is any data is missing
        Address address = addressService.createUserAddress(localUserDto);
        LocalUser newUser = localUserMapper.dtoToLocalUser(localUserDto);
        newUser.setAddress(address);

        localUserRepo.save(newUser);
        return newUser;
    }

    //get user and put it in db
    public LocalUser getUser(Long userID) {
        Optional<LocalUser> localUser = localUserRepo.findByUserID(userID);

        if(localUser.isEmpty()){
            throw new NullPointerException("User with ID \"" + userID + "\" does not exist.");
        }

        return localUser.get();
    }

    public LocalUser updateUser(long userID, LocalUserDto localUserDto) {
        Optional<LocalUser> localUser = localUserRepo.findByUserID(userID);

        if(localUser.isEmpty()){
            throw new NullPointerException("User with ID \"" + userID + "\" does not exist.");
        }

        LocalUser localUserEntity = localUser.get();
        Address address = localUserEntity.getAddress();

        localUserMapper.updateLocalUserFromDto(localUserDto, localUserEntity);
        //take the address and update all of its components using overloaded update method
        addressMapper.updateAddressFromDto(localUserDto, address);

        //save both components
        addressRepo.save(address);
        localUserRepo.save(localUserEntity);
        return localUserEntity;
    }


    //delete user and put it in db
    public void deleteUser(Long userID) {
        Optional<LocalUser> localUser = localUserRepo.findByUserID(userID);

        if(localUser.isEmpty()){
            throw new NullPointerException("User with ID \"" + userID + "\" does not exist.");
        }

        localUserRepo.delete(localUser.get());
    }
}
