package com.trainer.ticketbooking.service;

import com.trainer.ticketbooking.dto.LocalUserResponseDto;
import com.trainer.ticketbooking.mapper.AddressMapper;
import com.trainer.ticketbooking.mapper.LocalUserMapper;
import com.trainer.ticketbooking.repo.AddressRepo;
import com.trainer.ticketbooking.repo.LocalUserRepo;
import com.trainer.ticketbooking.entity.Address;
import com.trainer.ticketbooking.entity.LocalUser;
import com.trainer.ticketbooking.dto.LocalUserRequestDto;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
    public LocalUserResponseDto createUser(LocalUserRequestDto localUserRequestDto) {
        if(localUserRepo.findByUsernameIgnoreCase(localUserRequestDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException
                    ("User with username \"" + localUserRequestDto.getUsername() + "\" already exists.");
        }

        //address service will throw NPE is any data is missing
        Address address = addressService.createUserAddress(localUserRequestDto);
        LocalUser newUser = localUserMapper.dtoToLocalUser(localUserRequestDto);
        newUser.setAddress(address);

        localUserRepo.save(newUser);
        return localUserMapper.localUserToDto(newUser);
    }

    //get user and put it in db
    public LocalUserResponseDto getUser(Long userID) {
        Optional<LocalUser> localUser = localUserRepo.findByUserID(userID);

        if(localUser.isEmpty()){
            throw new NullPointerException("User with ID \"" + userID + "\" does not exist.");
        }

        return localUserMapper.localUserToDto(localUser.get());
    }

    public LocalUserResponseDto updateUser(long userID, LocalUserRequestDto localUserRequestDto) {
        Optional<LocalUser> optionalLocalUser = localUserRepo.findByUserID(userID);

        if(optionalLocalUser.isEmpty()){
            throw new NullPointerException("User with ID \"" + userID + "\" does not exist.");
        }

        LocalUser localUser = optionalLocalUser.get();
        Address address = localUser.getAddress();

        localUserMapper.updateLocalUserFromDto(localUserRequestDto, localUser);
        //take the address and update all of its components using overloaded update method
        addressMapper.updateAddressFromDto(localUserRequestDto, address);

        //save both components
        addressRepo.save(address);
        localUserRepo.save(localUser);
        return localUserMapper.localUserToDto(localUser);
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
